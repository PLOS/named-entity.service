package org.plos.namedentity.spring.config;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.rest.NamedEntityResource;
import org.plos.namedentity.service.NamedEntityService;
import org.springframework.context.annotation.Bean;

public class TestSpringConfig {

    @Bean
    static public NamedEntityService namedEntityService() {
        NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

        Mockito.when(mockNamedEntityService.create(any(TypedescriptionsDTO.class)))
            .thenAnswer(new Answer<TypedescriptionsDTO>() {
                @Override
                public TypedescriptionsDTO answer(InvocationOnMock invocation) throws Throwable {
                    Object[] args = invocation.getArguments();
                    TypedescriptionsDTO dto = (TypedescriptionsDTO) args[0];
                    dto.setTypeid(1);
                    return dto;
                }
            });

        Mockito.when(mockNamedEntityService.update(any(TypedescriptionsDTO.class)))
            .thenAnswer(new Answer<TypedescriptionsDTO>() {
                @Override
                public TypedescriptionsDTO answer(InvocationOnMock invocation) throws Throwable {
                    Object[] args = invocation.getArguments();
                    return (TypedescriptionsDTO) args[0];
                }
            });

        Mockito.when(mockNamedEntityService.findTypedescriptionById(eq(1)))
               .thenReturn(new TypedescriptionsDTO(1, "New Type Description", "New Type Usage"));
        
        return mockNamedEntityService;
    }

    @Bean
    static public NamedEntityResource namedEntityResource() {
        NamedEntityResource nedResource = new NamedEntityResource();
        nedResource.setNamedEntityService(namedEntityService());
        return nedResource;
    }
}
