package flm.sample.scs.deserialization.producer.core.application.service;

import flm.sample.scs.deserialization.producer.core.application.event.BusinessEntityCreatedDomainEvent;
import flm.sample.scs.deserialization.producer.core.domain.model.BusinessEntity;
import flm.sample.scs.deserialization.producer.core.domain.usecase.BusinessEntityCreationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class BusinessEntityService implements BusinessEntityCreationUseCase {

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
}
