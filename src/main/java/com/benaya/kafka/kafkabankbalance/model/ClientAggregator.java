package com.benaya.kafka.kafkabankbalance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientAggregator {

    private int balance;
    private int transactionCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTransactionTime;

    public ClientAggregator() {
        this.balance = 0;
        this.transactionCount = 0;
        this.lastTransactionTime = LocalDateTime.now();
    }
    public ClientAggregator addTransactionAndReturn(Transaction transaction) {
        this.balance += transaction.getAmount();
        this.transactionCount++;
        this.lastTransactionTime = transaction.getDateTime();
        return this;
    }
}
