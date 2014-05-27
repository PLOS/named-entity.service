package org.plos.namedentity.utils;

import java.util.ArrayList;
import java.util.List;

import org.plos.namedentity.api.dto.GlobaltypeDTO;
import org.plos.namedentity.api.dto.TypedescriptionDTO;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;

public class EntityPojoTransformer { 

    public static TypedescriptionEntity toEntity(TypedescriptionDTO dto) {
        return new TypedescriptionEntity(dto.getTypeid(), dto.getDescription(), dto.getHowused());
    }

    public static TypedescriptionDTO toPojo(TypedescriptionEntity entity) {
        return new TypedescriptionDTO(entity.getTypeid(), entity.getDescription(), entity.getHowused());
    }

    public static List<TypedescriptionDTO> toPojo(List<TypedescriptionEntity> entities) {
        List<TypedescriptionDTO> dtos = new ArrayList<>(entities.size());
        for(TypedescriptionEntity entity : entities) {
            dtos.add( toPojo(entity) ); 
        } 
        return dtos;
    }

    public static GlobaltypeEntity toEntity(GlobaltypeDTO dto) {
        return new GlobaltypeEntity(
            dto.getGlobaltypeid(),
            dto.getTypeid(),
            dto.getShortdescription(),
            dto.getLongdescription(),
            dto.getTypecode(),
            dto.getCreated(),
            dto.getLastmodified(),
            dto.getCreatedby(),
            dto.getLastmodifiedby());
    }
}
