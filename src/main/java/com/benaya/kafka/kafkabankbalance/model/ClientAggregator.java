package com.benaya.kafka.kafkabankbalance.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class ClientAggregator {

    private int balance;
    private int transactionCount;
    private Date lastTransactionTime;

    public ClientAggregator() {
        this.balance = 0;
        this.transactionCount = 0;
        this.lastTransactionTime = new Date();
    }
    public ClientAggregator addTransactionAndReturn(Transaction transaction) {
        this.balance += transaction.getAmount();
        this.transactionCount++;
        this.lastTransactionTime = transaction.getTime();
        return this;
    }
}
