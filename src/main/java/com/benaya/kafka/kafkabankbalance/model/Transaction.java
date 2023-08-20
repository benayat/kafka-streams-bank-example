package com.benaya.kafka.kafkabankbalance.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.util.Date;

@Data
public class Transaction {

    @JsonProperty("name")
    private String name;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("time")
    private Date time;

    public Transaction(String name) {
        this.name = name;
        this.amount = (int) (Math.random() * 1000);
        this.time = new Date();
    }

}
