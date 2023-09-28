package flm.sample.scs.deserialization.producer;

import flm.sample.scs.deserialization.producer.core.domain.model.BusinessEntity;
import flm.sample.scs.deserialization.producer.core.domain.usecase.BusinessEntityCreationUseCase;
import flm.sample.scs.deserialization.producer.core.domain.usecase.BusinessEntityDeletionUseCase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Stream;

@Component
public class ProducerApplicationRunner implements ApplicationRunner {

    private final BusinessEntityCreationUseCase businessEntityCreationUseCase;
    private final BusinessEntityDeletionUseCase businessEntityDeletionUseCase;

    public ProducerApplicationRunner(BusinessEntityCreationUseCase businessEntityCreationUseCase, BusinessEntityDeletionUseCase businessEntityDeletionUseCase) {
        this.businessEntityCreationUseCase = businessEntityCreationUseCase;
        this.businessEntityDeletionUseCase = businessEntityDeletionUseCase;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.generate(UUID::randomUUID)
              .limit(500)
              .forEach(uuid -> {
                          BusinessEntity businessEntity = BusinessEntity.builder()
                                                                        .id(uuid)
                                                                        .fieldOne("fieldOne")
                                                                        .fieldTwo("fieldTwo")
                                                                        .build();
                          businessEntityCreationUseCase.create(businessEntity);
                          businessEntityDeletionUseCase.delete(uuid);
                      }
              );
    }
}
