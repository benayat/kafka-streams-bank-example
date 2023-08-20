package com.benaya.kafka.kafkabankbalance.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.benaya.kafka.kafkabankbalance.constants.TopicsConstants.BANK_BALANCE_INPUT_TOPIC;
import static com.benaya.kafka.kafkabankbalance.constants.TopicsConstants.CLIENT_AGGREGATION_OUTPUT_TOPIC;

@Configuration
public class TopicConfig {
    @Bean
    public NewTopic bankBalanceTopic() {
        return TopicBuilder
                .name(BANK_BALANCE_INPUT_TOPIC)
                .build();
    }
    @Bean
    public NewTopic clientAggregationTopic() {
        return TopicBuilder
                .name(CLIENT_AGGREGATION_OUTPUT_TOPIC)
                .build();
    }


}
