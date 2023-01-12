package flm.sample.scs.deserialization.producer.core.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(of = {"id"})
@Builder
public class BusinessEntity {

    @Builder.Default
    UUID id = UUID.randomUUID();
    String fieldOne;
    String fieldTwo;
}
