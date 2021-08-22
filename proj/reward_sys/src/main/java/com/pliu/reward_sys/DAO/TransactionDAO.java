package com.pliu.reward_sys.DAO;

import com.pliu.reward_sys.entities.Customer;
import com.pliu.reward_sys.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class TransactionDAO {

    private static Map<Customer, Set<Transaction>> personalHistory = null;
    private static TreeMap<Long, Transaction> transactionMap = null;

    static {
        personalHistory = new HashMap<>();
        personalHistory.put(new Customer((long) 1001, 90,90),new HashSet<>(Arrays.asList(
                new Transaction((long)1001,(long)1,120, LocalDate.now())
        )));

        transactionMap = new TreeMap<>();
        transactionMap.put((long)1,new Transaction((long)1001,(long)1,120,LocalDate.now()) );
    }

    public void save(Customer customer, Transaction transaction){

        transaction.setId(transactionMap.lastKey() + 1);

        transactionMap.put(transaction.getId(),transaction);
        personalHistory.putIfAbsent(customer, new HashSet<>());
        personalHistory.get(customer).add(transaction);

    }

    public List<Transaction> findAll(){
        return new ArrayList<>(transactionMap.values());
    }

    public List<Transaction> findByCustomer(Customer customer){
        return  new ArrayList<>(personalHistory.get(customer));
    }



}
