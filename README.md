# Sample application for demonstrating the Spring Cloud Stream issue 2823

## Requirements
- Java 17
- A Kafka broker and a Schema Registry (*)

(*) A Docker Compose file, launching a Kafka broker and a Schema Registry, is available in "local-infrastructure/kafka".
Use the `docker-compose up -d` command to launch the Kafka broker and the Schema Registry.

## Steps to demonstrate the correct behaviour

When the consumers concurrency is set to 1 (*), all messages published by the producer are correctly consumed by the consumers.

(*) "spring.cloud.stream.kafka.streams.bindings.*-in-0.consumer.concurrency" properties must be set to 1 in place of 2 in the Consumer app application.yml

1. In the Consumer app application.yml, set the consumers concurrency to 1
2. Start the Consumer app
3. Start the Producer app (publishes 500 BusinessEntityCreatedEvent messages and 500 BusinessEntityDeletedEvent messages in the "business-entity-event" topic at startup)
4. No errors in the logs of the Consumer app

## Steps to reproduce the issue

When the consumers concurrency is set to 2 (*), some messages published by the producer are routed to the wrong consumer, resulting in java.lang.ClassCastException exceptions.

(*) "spring.cloud.stream.kafka.streams.bindings.*-in-0.consumer.concurrency" properties must be set to 2 in the Consumer app application.yml

1. In the Consumer app application.yml, set the consumers concurrency to 2
2. Start the Consumer app
3. Start the Producer app (publishes 500 BusinessEntityCreatedEvent messages and 500 BusinessEntityDeletedEvent messages in the "business-entity-event" topic at startup)
4. java.lang.ClassCastException errors in the logs of the Consumer app and shut down of the two streams clients
