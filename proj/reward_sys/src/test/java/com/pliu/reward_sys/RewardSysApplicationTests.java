package com.pliu.reward_sys;

import com.pliu.reward_sys.DAO.CustomerDAO;
import com.pliu.reward_sys.service.MyService;
import com.pliu.reward_sys.service.ReadFiler;
import org.assertj.core.util.diff.myers.MyersDiff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class RewardSysApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testCustomerDao(){
        CustomerDAO customerDAO = new CustomerDAO();
        long id = 1001;
        Assert.notNull(customerDAO.getById(id));
    }

    @Test
    public void testCalculator(){
        MyService myService = new MyService();

        System.out.println(myService.calculator(120));
        System.out.println(myService.calculator(20));
        System.out.println(myService.calculator(220));
        System.out.println(myService.calculator(55));

    }

    @Test
    public void testReadFiler(){
        ReadFiler readFiler = new ReadFiler();
        Assert.notNull(readFiler.readJson(),"huh?");
    }


}
