package flm.sample.scs.deserialization.producer.core.domain.usecase;

import flm.sample.scs.deserialization.producer.core.domain.model.BusinessEntity;

public interface BusinessEntityCreationUseCase {

    void create(BusinessEntity businessEntity);
}
