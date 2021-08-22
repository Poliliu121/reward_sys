package com.pliu.reward_sys.controller;

import com.pliu.reward_sys.entities.Customer;
import com.pliu.reward_sys.entities.Transaction;
import com.pliu.reward_sys.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Collection;


@Controller
public class RESTController {

    @Autowired
    MyService myService;

    /**
     * Get all the info from back, and put them to front
     * @param modelAndView
     * @return
     */
    @GetMapping("/*")
    public ModelAndView homePage(ModelAndView modelAndView){
        System.out.println(modelAndView.toString());
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
    @GetMapping("/cal")
    public ModelAndView calculator(@RequestParam int cost){

        ModelAndView modelAndView = new ModelAndView();
        Collection<Customer> customerCollection = myService.findAllCustomers();
        Collection<Transaction> transactionsCollection = myService.findAllTransaction();
        modelAndView.addObject("customers",customerCollection);
        modelAndView.addObject("transactions",transactionsCollection);

        int credit = myService.calculator(cost);
        modelAndView.addObject("calculator",true);
        modelAndView.addObject("cost",cost);
        modelAndView.addObject("credit",credit);
        modelAndView.setViewName("index");

        return modelAndView;
    }



    @PostMapping("/add")
    public String addTransaction(Long customerId, Long Id, Integer cost, String date){
        LocalDate localDate = LocalDate.parse(date);

        Transaction transaction = new Transaction(customerId,Id,cost,localDate);
        Customer customer = myService.editnewInfo(transaction);
        myService.saveOrUpdateCustomer(customer);
        myService.saveTransaction(customer,transaction);

        return "redirect:/index";
    }
}
