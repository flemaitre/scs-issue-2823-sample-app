package flm.sample.scs.deserialization.consumer.infrastructure.messaging.consumer;

import kafka.event.BusinessEntityDeletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
class BusinessEntityDeletedEventConsumer {

    @Bean
    public Consumer<KStream<String, BusinessEntityDeletedEvent>> processBusinessEntityDeletedEvent() {
        return events -> events.foreach((key, value) -> {
            log.info("Deleted event for business entity id={} consumed", value.getBusinessEntityId());
        });
    }
}
