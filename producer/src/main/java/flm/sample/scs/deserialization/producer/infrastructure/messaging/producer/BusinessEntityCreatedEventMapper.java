package flm.sample.scs.deserialization.producer.infrastructure.messaging.producer;

import flm.sample.scs.deserialization.producer.core.application.event.BusinessEntityCreatedDomainEvent;
import kafka.event.BusinessEntityCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

@Mapper(componentModel = "spring")
interface BusinessEntityCreatedEventMapper {

    @Mapping(target = "creationDateTime", source = "creationDateTime", qualifiedByName = "toEpochSeconds")
    BusinessEntityCreatedEvent toAvro(BusinessEntityCreatedDomainEvent event);

    @Named("toEpochSeconds")
    default Long toEpochSeconds(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.toEpochSecond(UTC);
    }
}
