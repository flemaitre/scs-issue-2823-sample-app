package flm.sample.scs.deserialization.producer.core.domain.usecase;

import java.util.UUID;

public interface BusinessEntityDeletionUseCase {

    void delete(UUID businessEntityId);
}
