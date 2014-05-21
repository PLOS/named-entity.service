package org.plos.namedentity.spring.config;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.rest.NamedEntityResource;
import org.plos.namedentity.service.NamedEntityService;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;

public class TestSpringConfig {

    @Bean @SuppressWarnings("unchecked")
    static public NamedEntityService namedEntityService() {
        NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

        when(mockNamedEntityService.create(any(TypedescriptionsDTO.class)))
            .thenReturn(Integer.valueOf(1))
                .thenThrow(NedValidationException.class)
                    .thenThrow(RuntimeException.class);

        when(mockNamedEntityService.update(any()))
            .thenReturn(true)
                .thenThrow(NedValidationException.class)
                    .thenThrow(RuntimeException.class);

        when(mockNamedEntityService.delete(any()))
            .thenReturn(true)
                .thenThrow(RuntimeException.class);

        when(mockNamedEntityService.findById(eq(1), eq(TypedescriptionsDTO.class)))
            .thenReturn(new TypedescriptionsDTO(1, "New Type Description", "New Type Usage"));

        List<TypedescriptionsDTO> typeClassList = new ArrayList<>();
        typeClassList.add(new TypedescriptionsDTO(1, "Type Description1", "Type Usage1"));
        typeClassList.add(new TypedescriptionsDTO(2, "Type Description2", "Type Usage2"));
        typeClassList.add(new TypedescriptionsDTO(3, "Type Description3", "Type Usage3"));

        when(mockNamedEntityService.findAll(eq(TypedescriptionsDTO.class))).thenReturn(typeClassList);
        
        return mockNamedEntityService;
    }

    @Bean
    static public NamedEntityResource namedEntityResource() {
        NamedEntityResource nedResource = new NamedEntityResource();
        nedResource.setNamedEntityService(namedEntityService());
        return nedResource;
    }
}
