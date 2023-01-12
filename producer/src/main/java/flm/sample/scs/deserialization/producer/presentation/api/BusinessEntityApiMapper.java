package flm.sample.scs.deserialization.producer.presentation.api;

import flm.sample.scs.deserialization.producer.core.domain.model.BusinessEntity;
import flm.sample.scs.deserialization.producer.presentation.api.model.BusinessEntityApiBean;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface BusinessEntityApiMapper {

    @Mapping(target = "id", ignore = true)
    BusinessEntity toDomain(BusinessEntityApiBean businessEntity);
}
