package com.pliu.reward_sys.service;

import com.pliu.reward_sys.DAO.CustomerDAO;
import com.pliu.reward_sys.DAO.TransactionDAO;
import com.pliu.reward_sys.entities.Customer;
import com.pliu.reward_sys.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import java.time.Period;
import java.util.List;

@Service
public class MyService {
    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;
    private ReadFiler readFiler;

    @Autowired
    public void setCustomerDAO(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @Autowired
    public void setTransactionDAO(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }

    @Autowired
    public void setReadFiler(ReadFiler readFiler){
        this.readFiler = readFiler;
    }

    @PostConstruct
    public void init (){
        System.out.println("Init Loading..");
        List<Transaction> transactionList = readDataSet();
        for(Transaction tran : transactionList){
            Long customer_Id = tran.getCustomerId();
            int cost = calculator(tran.getTotal_cost());

            Customer customer = getCustomerById(customer_Id);
            int totalCredits = customer.getTotal_Credits(), threeMonthCredit = customer.getThreeMonthCredits();
            totalCredits += cost;
            if(isMonth(tran)){
                threeMonthCredit += cost;
            }
            customer.setTotal_Credits(totalCredits);
            customer.setThreeMonthCredits(threeMonthCredit);

            saveOrUpdateCustomer(customer);
            saveTransaction(customer,tran);

        }
    }

    /**
     * Calculate credit
     * @param cost
     * @return
     */
    public int calculator(int cost){
        int sum =0;
        cost -= 50;
        if(cost>50){
            sum += 50 + (cost -50)*2;
        }else if(cost >0){
            sum += cost;
        }
        return sum;
    }

    /***
     * Read test data from data.json
     * @return
     */
    private List<Transaction> readDataSet(){
        return readFiler.readJson();
    }

    /**
     * Check month diff
     * @param t
     * @return
     */

    private boolean isMonth(Transaction t){
        LocalDate date = t.getTransDate();
        Period period = Period.between(LocalDate.now(),date);
        int month = Math.abs(period.getMonths());
        int year = Math.abs(period.getYears());

        return month<=3 && year == 0;
    }



    public List<Customer> findAllCustomers(){
        return customerDAO.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerDAO.getById(id);
    }

    public void saveOrUpdateCustomer(Customer customer){
        customerDAO.saveOrUpdate(customer.getId(), customer);
    }

    public void saveTransaction(Customer customer, Transaction transaction){
        transactionDAO.save(customer,transaction);
    }

    public List<Transaction> findAllTransaction(){
        return transactionDAO.findAll();
    }

    public List<Transaction> findByCustomerId(Customer customer){
        return transactionDAO.findByCustomer(customer);
    }

    public Customer editnewInfo(Transaction transaction){
        Customer customer = customerDAO.getById(transaction.getCustomerId());
        int credits = calculator(transaction.getTotal_cost());
        int credits3 = (isMonth(transaction))?credits : 0;
        customer.setTotal_Credits(customer.getTotal_Credits() + credits);
        customer.setThreeMonthCredits(customer.getThreeMonthCredits() + credits3);

        return customer;
    }



}
