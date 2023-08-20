package com.benaya.kafka.kafkabankbalance.config;

import com.benaya.kafka.kafkabankbalance.constants.TopicsConstants;
import com.benaya.kafka.kafkabankbalance.model.ClientAggregator;
import com.benaya.kafka.kafkabankbalance.model.Transaction;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
@EnableKafkaStreams
public class StreamsBuildConfig {

    @Bean(name = "BANK_BALANCE_INPUT_STREAM")
    public KStream<String, Transaction> getInputStream(StreamsBuilder streamsBuilder) {
        return streamsBuilder.stream(TopicsConstants.BANK_BALANCE_INPUT_TOPIC, Consumed.with(Serdes.String(), new JsonSerde<>(Transaction.class)));
    }
    @Bean
    public KTable<String, ClientAggregator> transactionKStream(@Qualifier("BANK_BALANCE_INPUT_STREAM") KStream<String, Transaction> transactionInputStream) {
        KTable<String, ClientAggregator> clientAggregatorKTable = transactionInputStream
                .groupByKey()
                .aggregate(ClientAggregator::new, (name, transaction, clientAggregator) -> clientAggregator.addTransactionAndReturn(transaction));
        clientAggregatorKTable.toStream().to(TopicsConstants.CLIENT_AGGREGATION_OUTPUT_TOPIC);
        return clientAggregatorKTable;
    }
}
