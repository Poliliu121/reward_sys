package com.pliu.reward_sys.entities;


import lombok.Data;

import java.util.Objects;

@Data
public class Customer {
    private Long id;
    private Integer total_Credits;
    private Integer threeMonthCredits;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal_Credits() {
        return total_Credits;
    }

    public void setTotal_Credits(Integer total_Credits) {
        this.total_Credits = total_Credits;
    }

    public Integer getThreeMonthCredits() {
        return threeMonthCredits;
    }

    public void setThreeMonthCredits(Integer threeMonthCredits) {
        this.threeMonthCredits = threeMonthCredits;
    }

    public Customer() {
    }

    public Customer(Long id, Integer total_Credits, Integer threeMonthCredits) {
        this.id = id;
        this.total_Credits = total_Credits;
        this.threeMonthCredits = threeMonthCredits;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", total_Credits=" + total_Credits +
                ", threeMonthCredits=" + threeMonthCredits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
