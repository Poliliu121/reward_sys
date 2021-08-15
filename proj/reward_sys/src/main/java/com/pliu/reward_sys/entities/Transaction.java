package com.pliu.reward_sys.entities;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class Transaction {

    private Long customerId;
    private Long Id;
    private Integer total_cost;
    private LocalDate transDate;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public Integer getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Integer total_cost) {
        this.total_cost = total_cost;
    }


    public Transaction() {}

    public Transaction(Long customerId, Long id, Integer total_cost, LocalDate transDate) {
        this.customerId = customerId;
        Id = id;
        this.total_cost = total_cost;
        this.transDate = transDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customerId=" + customerId +
                ", Id=" + Id +
                ", total_cost=" + total_cost +
                ", transDate=" + transDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Id.equals(that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
