package flm.sample.scs.deserialization.producer.core.application.event;

import flm.sample.scs.deserialization.producer.core.domain.model.BusinessEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;

@Value
@EqualsAndHashCode(callSuper = true)
public class BusinessEntityCreatedDomainEvent extends ApplicationEvent {

    Instant creationDateTime;

    @Builder
    public BusinessEntityCreatedDomainEvent(BusinessEntity businessEntity, Instant creationDateTime) {
        super(businessEntity);
        this.creationDateTime = creationDateTime;
    }

    public BusinessEntity getBusinessEntity() {
        return (BusinessEntity) super.getSource();
    }
}
