package org.plos.namedentity.spring.config;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;
import org.plos.namedentity.rest.NamedEntityResource;
import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.service.NamedEntityServiceHighApi;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;

public class TestSpringConfig {

    @Bean @SuppressWarnings("unchecked")
    static public NamedEntityService namedEntityService() {
        NamedEntityService mockNamedEntityService =  Mockito.mock(NamedEntityService.class);

        when(mockNamedEntityService.create(any()))
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

        // TYPE DESCRIPTIONS (TYPE CLASSES)

        when(mockNamedEntityService.findById(eq(1), eq(TypedescriptionEntity.class)))
            .thenReturn(new TypedescriptionEntity(1, "New Type Description", "New Type Usage"));

        List<TypedescriptionEntity> typeClassList = new ArrayList<>();
        typeClassList.add(new TypedescriptionEntity(1, "Type Description1", "Type Usage1"));
        typeClassList.add(new TypedescriptionEntity(2, "Type Description2", "Type Usage2"));
        typeClassList.add(new TypedescriptionEntity(3, "Type Description3", "Type Usage3"));

        when(mockNamedEntityService.findAll(eq(TypedescriptionEntity.class))).thenReturn(typeClassList);

        // TYPE VALUES (GLOBAL TYPES)

        GlobaltypeEntity typeVal = new GlobaltypeEntity();
        typeVal.setGlobaltypeid(1);
        typeVal.setTypeid(1);
        typeVal.setShortdescription("Type Value #1 Short Description");
        typeVal.setTypecode("TV1");

        when(mockNamedEntityService.findById(eq(1), eq(GlobaltypeEntity.class))).thenReturn(typeVal);

        List<GlobaltypeEntity> typeValuesForTypeClass = new ArrayList<>();
        for (int i = 1; i <=5; i++) {
            typeValuesForTypeClass.add(new GlobaltypeEntity(
                i, 1, "shortdesc"+i, "longdesc"+i, "typ"+i, null, null, null, null));
        }

        when(mockNamedEntityService.findByAttribute(isA(GlobaltypeEntity.class))).thenReturn(typeValuesForTypeClass);

        return mockNamedEntityService;
    }

    @Bean @SuppressWarnings("unchecked")
    static public NamedEntityServiceHighApi namedEntityServiceHighApi() {
        NamedEntityServiceHighApi mockNamedEntityServiceHighApi =  Mockito.mock(NamedEntityServiceHighApi.class);
        //TODO
        return mockNamedEntityServiceHighApi;
    }

    @Bean
    static public NamedEntityResource namedEntityResource() {
        NamedEntityResource nedResource = new NamedEntityResource();
        nedResource.setNamedEntityService(namedEntityService());
        return nedResource;
    }
}
