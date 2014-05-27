package org.plos.namedentity.utils;

import java.util.List;

import org.plos.namedentity.api.dto.GlobaltypeDTO;
import org.plos.namedentity.api.dto.TypedescriptionDTO;
import org.plos.namedentity.api.entity.GlobaltypeEntity;
import org.plos.namedentity.api.entity.TypedescriptionEntity;

public interface Transformer { 

    public TypedescriptionEntity    toEntity(TypedescriptionDTO dto);
    public TypedescriptionDTO       toPojo(TypedescriptionEntity entity);
    public List<TypedescriptionDTO> toPojo(List<TypedescriptionEntity> entities);

    public GlobaltypeEntity toEntity(GlobaltypeDTO dto);
}
