package org.plos.namedentity.service;

import java.util.Collection;

import org.plos.namedentity.api.TypedescriptionsDTO;

public interface NamedEntityService {

    public TypedescriptionsDTO create(TypedescriptionsDTO typeDescription);
    public TypedescriptionsDTO update(TypedescriptionsDTO typeDescription);
    public boolean delete(TypedescriptionsDTO typeDescription);

    public TypedescriptionsDTO findTypedescriptionById(Integer typeId);
    public Collection<TypedescriptionsDTO> getTypedescriptions();
}
