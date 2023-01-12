# Sample application for demonstrating the Spring Cloud Stream issue 2613

## Requirements
- Java 17
- A Kafka broker and a Schema Registry (*)

(*) A Docker Compose file, launching a Kafka broker and a Schema Registry, is available in "local-infrastructure/kafka".
Use the `docker-compose up -d` command to launch the Kafka broker and the Schema Registry.

## Steps to demonstrate the correct behaviour

When not using event types routing (*), the "on deserialization error" message is skipped and sent to the Dead Letter Topic.

(*) "spring.cloud.stream.kafka.streams.binding.processBusinessEntityCreatedEvent-in-0.consumer.event-types" property commented in the Consumer application.yml

1. Start the Consumer app
2. Start the Producer app
3. Send a message to Kafka: `curl -X POST -H "Content-Type: application/json" -d "{ \"fieldOne\": \"valueOne\" }" http://localhost:8070/api/v1/entities`

## Steps to reproduce the issue

When using event types routing, the "on deserialization error" message is not sent to the Dead Letter Topic and the consumer (Stream client) is shutdown.

1. Uncomment the "spring.cloud.stream.kafka.streams.binding.processBusinessEntityCreatedEvent-in-0.consumer.event-types" property in the Consumer application.yml
2. Start the Consumer app
3. Start the Producer app
4. Send a message to Kafka: `curl -X POST -H "Content-Type: application/json" -d "{ \"fieldOne\": \"valueOne\" }" http://localhost:8070/api/v1/entities`
