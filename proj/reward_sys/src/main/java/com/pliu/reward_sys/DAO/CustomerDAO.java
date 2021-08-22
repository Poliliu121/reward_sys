package com.pliu.reward_sys.DAO;


import com.pliu.reward_sys.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class CustomerDAO {
    // Take this map as a table to save our data.
    private static Map<Long, Customer> customersMap = null;

    static {
        customersMap = new HashMap<>();
        customersMap.put((long) 1001,new Customer((long) 1001, 90,90));
    }

    public List<Customer> findAll(){
        return new ArrayList<>(customersMap.values());
    }

    public Customer getById(Long Id){
        return customersMap.getOrDefault(Id,new Customer(Id,0,0));
    }

    public void saveOrUpdate(Long id, Customer customer){
        customersMap.put(id,customer);
    }


}
