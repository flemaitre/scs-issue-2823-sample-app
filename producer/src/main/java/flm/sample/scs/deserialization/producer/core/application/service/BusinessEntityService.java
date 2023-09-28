package flm.sample.scs.deserialization.producer.core.application.service;

import flm.sample.scs.deserialization.producer.core.application.event.BusinessEntityCreatedDomainEvent;
import flm.sample.scs.deserialization.producer.core.application.event.BusinessEntityDeletedDomainEvent;
import flm.sample.scs.deserialization.producer.core.domain.model.BusinessEntity;
import flm.sample.scs.deserialization.producer.core.domain.usecase.BusinessEntityCreationUseCase;
import flm.sample.scs.deserialization.producer.core.domain.usecase.BusinessEntityDeletionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.time.Instant.now;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class BusinessEntityService implements BusinessEntityCreationUseCase, BusinessEntityDeletionUseCase {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public BusinessEntityService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void create(BusinessEntity businessEntity) {
        notNull(businessEntity, "businessEntity is mandatory");

        BusinessEntityCreatedDomainEvent event = BusinessEntityCreatedDomainEvent.builder().businessEntity(businessEntity).creationDateTime(now()).build();
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void delete(UUID businessEntityId) {
        notNull(businessEntityId, "businessEntityId is mandatory");

        BusinessEntityDeletedDomainEvent event = BusinessEntityDeletedDomainEvent.builder().businessEntityId(businessEntityId).deletionDateTime(now()).build();
        applicationEventPublisher.publishEvent(event);
    }
}
