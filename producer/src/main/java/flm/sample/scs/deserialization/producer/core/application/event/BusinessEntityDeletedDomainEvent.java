package flm.sample.scs.deserialization.producer.core.application.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class BusinessEntityDeletedDomainEvent extends ApplicationEvent {

    Instant deletionDateTime;

    @Builder
    public BusinessEntityDeletedDomainEvent(UUID businessEntityId, Instant deletionDateTime) {
        super(businessEntityId);
        this.deletionDateTime = deletionDateTime;
    }

    public UUID getBusinessEntityId() {
        return (UUID) super.getSource();
    }
}
