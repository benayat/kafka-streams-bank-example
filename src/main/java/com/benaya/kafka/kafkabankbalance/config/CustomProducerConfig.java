package com.benaya.kafka.kafkabankbalance.config;

import com.benaya.kafka.kafkabankbalance.model.Transaction;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Collections;

@Configuration
public class CustomProducerConfig {
    @Bean(name = "clientToTransactionKafkaTemplate")
    public KafkaTemplate<String, Transaction> bytesTemplate(ProducerFactory<String, Transaction> pf) {
        return new KafkaTemplate<>(pf,
                Collections.singletonMap(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class));
    }
}
