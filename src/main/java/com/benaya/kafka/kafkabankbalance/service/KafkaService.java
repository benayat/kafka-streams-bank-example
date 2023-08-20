package com.benaya.kafka.kafkabankbalance.service;

import com.benaya.kafka.kafkabankbalance.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public CompletableFuture<SendResult<String,Transaction>> sendTransactionMessage(Transaction transaction) {
        return kafkaTemplate.send("bank-balance-input", transaction.getName(), transaction);
    }

    @Scheduled(fixedDelay = 30)
    public void sendBalance() {
        sendTransactionMessage(new Transaction("ben"));
        sendTransactionMessage(new Transaction("ohad"));
        sendTransactionMessage(new Transaction("aba"));
    }
}
