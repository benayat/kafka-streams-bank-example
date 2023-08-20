package com.benaya.kafka.kafkabankbalance.config;

import com.benaya.kafka.kafkabankbalance.constants.TopicsConstants;
import com.benaya.kafka.kafkabankbalance.model.ClientAggregator;
import com.benaya.kafka.kafkabankbalance.model.Transaction;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
@EnableKafkaStreams
public class StreamsBuildConfig {
    @Bean
    public KTable<String, ClientAggregator> transactionKStream(StreamsBuilder streamsBuilder) {
        KTable<String, ClientAggregator> clientAggregatorKTable = streamsBuilder.stream(TopicsConstants.BANK_BALANCE_INPUT_TOPIC, Consumed.with(Serdes.String(), new JsonSerde<>(Transaction.class)))
                .groupByKey()
                .aggregate(ClientAggregator::new,
                        (name, transaction, clientAggregator) -> clientAggregator.addTransactionAndReturn(transaction),
                        Materialized.with(Serdes.String(), new JsonSerde<>(ClientAggregator.class)));
        clientAggregatorKTable.toStream().to(TopicsConstants.CLIENT_AGGREGATION_OUTPUT_TOPIC);
        clientAggregatorKTable.toStream().print(Printed.<String, ClientAggregator>toSysOut().withLabel("client-aggregation-printer"));
        return clientAggregatorKTable;
    }
}
