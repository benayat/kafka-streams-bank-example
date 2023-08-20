package com.benaya.kafka.kafkabankbalance.service;

import com.benaya.kafka.kafkabankbalance.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.benaya.kafka.kafkabankbalance.constants.TopicsConstants.BANK_BALANCE_INPUT_TOPIC;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class KafkaService {

    @Qualifier("clientToTransactionKafkaTemplate")
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public CompletableFuture<SendResult<String,Transaction>> sendTransactionMessage(Transaction transaction) {
        ProducerRecord<String, Transaction> producerRecord = new ProducerRecord<>(BANK_BALANCE_INPUT_TOPIC, transaction.getName(), transaction);
        return kafkaTemplate.send(producerRecord);
    }

    @Scheduled(fixedDelay = 30)
    public void sendBalance() {
        sendTransactionMessage(new Transaction("ben"));
        sendTransactionMessage(new Transaction("ohad"));
        sendTransactionMessage(new Transaction("aba"));
    }
}
