package com.benaya.kafka.kafkabankbalance.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Transaction implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("amount")
    private int amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    public Transaction(String name) {
        this.name = name;
        this.amount = (int) (Math.random() * 2000) - 1000;
        this.dateTime = LocalDateTime.now();
    }

}
