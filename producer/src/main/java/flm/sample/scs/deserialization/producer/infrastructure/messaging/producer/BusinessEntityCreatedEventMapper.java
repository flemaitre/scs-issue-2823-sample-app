package flm.sample.scs.deserialization.producer.infrastructure.messaging.producer;

import flm.sample.scs.deserialization.producer.core.application.event.BusinessEntityCreatedDomainEvent;
import kafka.event.BusinessEntityCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;

@Mapper(componentModel = "spring")
interface BusinessEntityCreatedEventMapper {

    @Mapping(target = "creationDateTime", source = "creationDateTime", qualifiedByName = "toEpochMilliseconds")
    BusinessEntityCreatedEvent toAvro(BusinessEntityCreatedDomainEvent event);

    @Named("toEpochMilliseconds")
    default Long toEpochMilliseconds(Instant instant) {
        return instant == null ? null : instant.toEpochMilli();
    }
}
