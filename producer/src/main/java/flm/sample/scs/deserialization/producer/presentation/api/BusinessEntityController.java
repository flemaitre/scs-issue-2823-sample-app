package flm.sample.scs.deserialization.producer.presentation.api;

import flm.sample.scs.deserialization.producer.core.domain.model.BusinessEntity;
import flm.sample.scs.deserialization.producer.core.domain.usecase.BusinessEntityCreationUseCase;
import flm.sample.scs.deserialization.producer.presentation.api.model.BusinessEntityApiBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1")
public class BusinessEntityController implements BusinessEntityApi {

    private final BusinessEntityCreationUseCase businessEntityCreationUseCase;
    private final BusinessEntityApiMapper businessEntityApiMapper;

    @Autowired
    public BusinessEntityController(BusinessEntityCreationUseCase businessEntityCreationUseCase, BusinessEntityApiMapper businessEntityApiMapper) {
        this.businessEntityCreationUseCase = businessEntityCreationUseCase;
        this.businessEntityApiMapper = businessEntityApiMapper;
    }

    @Override
    public ResponseEntity<Void> createBusinessEntity(BusinessEntityApiBean businessEntityApiBean) {
        BusinessEntity businessEntity = businessEntityApiMapper.toDomain(businessEntityApiBean);
        businessEntityCreationUseCase.create(businessEntity);

        return ResponseEntity.created(fromCurrentRequest().path("/{id}").buildAndExpand(businessEntity.getId()).toUri()).build();
    }
}
