package org.plos.namedentity.persist;

import java.util.List;
import java.io.Serializable;

import org.plos.namedentity.api.*;

import org.springframework.transaction.annotation.Transactional;

public interface NamedEntityDBService {

    @Transactional public Integer newNamedEntityId(String typeCode);
    @Transactional public Integer create(Serializable entity);
    @Transactional public boolean update(Serializable entity);
    @Transactional public boolean delete(Serializable entity);

    // TODO: DAO per table? ugh.
    public List<TypedescriptionsDTO> findTypedescriptionAll();
    public TypedescriptionsDTO       findTypedescriptionById(Integer id);
}
