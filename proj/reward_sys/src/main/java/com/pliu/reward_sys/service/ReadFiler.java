package com.pliu.reward_sys.service;

import com.pliu.reward_sys.entities.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ReadFiler{

    public List<Transaction> readJson(){
        List<Transaction> list = new LinkedList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Resource resource = new ClassPathResource("data.json");
        try {

            File resourceFile = resource.getFile();
            String rawJson = reader(resourceFile);
            JSONObject root = new JSONObject(rawJson);
            JSONArray purchases = root.getJSONArray("transactions");
            for(int i = 0;i<purchases.length();i++){

                JSONObject jsonObject = purchases.getJSONObject(i);

                long customer_id= jsonObject.getLong("customer_id");
                long id = jsonObject.getLong("id");
                int cost = jsonObject.getInt("cost");
                LocalDate date = LocalDate.parse(jsonObject.getString("date"), formatter);

                Transaction transaction = new Transaction(customer_id,id,cost,date);

                list.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public String reader(File file){
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                buffer.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(scanner != null) scanner.close();
        }

        return buffer.toString();
    }



}
