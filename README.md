# Client Transaction Processing with Kafka Streams

This application demonstrates real-time transaction processing using Kafka Streams in a Spring Boot application. It consists of a producer that generates 100 transactions per second and a stream processor that aggregates the data to provide insights into client transactions.

## Overview

### Producer
The producer component generates 100 transactions per second, each containing client name, transaction amount, and transaction date. These transactions are sent to a Kafka topic named \`transactions-topic\`.

### Stream Processor
The stream processor consumes the transactions from the \`bank-balance-input\`, groups them by client name, and calculates the following for each client:
- Current balance
- Number of transactions so far
- Last transaction time

The aggregated data is maintained in a \`KTable\`, as well as sent to the \`client-aggregation-output`\ topic, allowing for real-time querying and further processing.

## Architecture
- **docker-compose**: Used to run Kafka and Zookeeper in Docker containers.
- **Kafka**: Used for real-time data streaming.
- **Spring Boot**: Framework for building the application.
- **Kafka Streams**: Library used for stream processing.

## Prerequisites
- docker
- Java 8 or higher
- Apache Kafka
- Maven (for building the project)

## Setup

1. **Start Kafka**: Start Zookeeper and Kafka using docker-compose:
   \```bash
   docker-compose up
   \```

2. **Create Topic**: all topics are created within the app, so no need to do it manually. 

3. **Build the Project**: Navigate to the project directory and run:
   \```bash
   mvn clean install
   \```

4. **Run the Application**: Start the Spring Boot application:
   \```bash
   java -jar target/your-app-name.jar
   \```

## Usage

Once the application is running, the producer will start generating transactions, and the stream processor will begin aggregating the data. You can further extend the application to expose the aggregated data through REST endpoints or send it to another Kafka topic.

## Contributing

Feel free to submit issues, fork the repository, and send pull requests!

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
