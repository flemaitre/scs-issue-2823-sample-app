package flm.sample.scs.deserialization.producer.infrastructure.messaging.producer;

import flm.sample.scs.deserialization.producer.core.application.event.BusinessEntityDeletedDomainEvent;
import kafka.event.BusinessEntityDeletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
public class BusinessEntityDeletedEventProducer {

    private static final String BUSINESS_EVENT_TOPIC = "business-entity-event";
    private static final String EVENT_TYPE_HEADER_KEY = "event_type";
    private static final byte[] EVENT_TYPE_HEADER_VALUE = "business-entity-deleted-event".getBytes(UTF_8);

    private final KafkaTemplate<String, BusinessEntityDeletedEvent> kafkaTemplate;

    @Autowired
    public BusinessEntityDeletedEventProducer(KafkaTemplate<String, BusinessEntityDeletedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    @EventListener
    public void publishBusinessEntityCreatedDomainEvent(BusinessEntityDeletedDomainEvent event) {
        String kafkaEventKey = event.getBusinessEntityId().toString();
        BusinessEntityDeletedEvent kafkaEventValue = new BusinessEntityDeletedEvent(event.getBusinessEntityId(), event.getDeletionDateTime().toEpochMilli());

        Header eventTypeHeader = new RecordHeader(EVENT_TYPE_HEADER_KEY, EVENT_TYPE_HEADER_VALUE);
        ProducerRecord<String, BusinessEntityDeletedEvent> producerRecord =
                new ProducerRecord<>(BUSINESS_EVENT_TOPIC, null, kafkaEventKey, kafkaEventValue, new RecordHeaders(List.of(eventTypeHeader)));
        try {
            kafkaTemplate.send(producerRecord);
            log.info("Deleted event for business entity id={} published", kafkaEventKey);
        } catch (Exception e) {
            log.error("Error while publishing deleted event for business entity id={}", kafkaEventKey, e);
        }
    }
}
