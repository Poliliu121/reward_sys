package com.pliu.reward_sys.controller;

import com.pliu.reward_sys.entities.Customer;
import com.pliu.reward_sys.entities.Transaction;
import com.pliu.reward_sys.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class RESTctrl {

    @Autowired
    MyService myService;

    /**
     * Get all the info from back, and put them to front
     * @param modelAndView
     * @return
     */
    @GetMapping("/*")
    public ModelAndView homePage(ModelAndView modelAndView){

        Collection<Customer> customerCollection = myService.findAllCustomers();
        Collection<Transaction> transactionsCollection = myService.findAllTransaction();
        modelAndView.addObject("customers",customerCollection);
        modelAndView.addObject("transactions",transactionsCollection);
        modelAndView.setViewName("/index");

        return modelAndView;
    }

    /**
     * Mapping with Calculator
     * @param cost
     * @return
     */
    @PostMapping("/cal")
    public ModelAndView calculator(@RequestParam("cost") int cost){

        ModelAndView modelAndView = new ModelAndView("redirect:/index.html");

        int credit = myService.calculator(cost);
        modelAndView.addObject("calculator",true);
        modelAndView.addObject("cost",cost);
        modelAndView.addObject("credit",credit);

        return modelAndView;
    }

    /**
     * Redirect part
     *
     * @param calculator
     * @param cost
     * @param credit
     * @return
     */

    @GetMapping("/index.html") // calculator=true&cost=123&credit=96
    public ModelAndView redirector(@RequestParam("calculator") boolean calculator,
                                   @RequestParam("cost") int cost,
                                   @RequestParam("credit") int credit){
        ModelAndView modelAndView = new ModelAndView("/index");
        if(calculator){
            modelAndView.addObject("cost", cost);
            modelAndView.addObject("credit", credit);
        }
        Collection<Customer> customerCollection = myService.findAllCustomers();
        Collection<Transaction> transactionsCollection = myService.findAllTransaction();
        modelAndView.addObject("customers",customerCollection);
        modelAndView.addObject("transactions",transactionsCollection);
        modelAndView.addObject("calculator",calculator);

        return modelAndView;
    }
}
