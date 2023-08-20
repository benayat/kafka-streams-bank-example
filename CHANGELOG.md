## CHANGELOG
#### 0.0.1-SNAPSHOT
- Initial code. 
- configs: 3 files, CustomProducerConfig class for producer setup, KafkaBasicConfig for kafka streams properties, StreamBuildConfig for the streams definition, and TopicConfig for topics.
- set constants package for avoiding hardcoded topic names and streams.
- set Transaction model for the data to be streamed.
- set the KafkaService for using the kafka template and send data to the topic.

#### 1.0.0
- set the relevant serdes for the data to be streamed.
- added the ClientAggregator class for the aggregation logic.