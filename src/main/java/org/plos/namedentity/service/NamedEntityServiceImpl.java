package org.plos.namedentity.service;

import java.util.Collection;
import javax.inject.Inject;

import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.persist.NamedEntityDBService;

public class NamedEntityServiceImpl implements NamedEntityService {

    @Inject private NamedEntityDBService namedEntityDBService; 

    @Override
    public TypedescriptionsDTO create(TypedescriptionsDTO typeDescription) {
        // TODO: exception handling
        return namedEntityDBService.findTypedescriptionById(
            namedEntityDBService.create(typeDescription));
    }

    @Override
    public TypedescriptionsDTO update(TypedescriptionsDTO typeDescription) {
        // TODO: exception handling
        namedEntityDBService.update(typeDescription);
        return namedEntityDBService.findTypedescriptionById(typeDescription.getTypeid());
    }

    @Override
    public boolean delete(TypedescriptionsDTO typeDescription) {
        return namedEntityDBService.delete(typeDescription);
    }

    @Override
    public TypedescriptionsDTO findTypedescriptionById(Integer typeId) {
        return namedEntityDBService.findTypedescriptionById(typeId);
    }

    @Override
    public Collection<TypedescriptionsDTO> getTypedescriptions() {
        return namedEntityDBService.findTypedescriptionAll();
    }
    
    public NamedEntityDBService getNamedEntityDBService() {
        return namedEntityDBService;
    }
    
    public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
        this.namedEntityDBService = namedEntityDBService;
    }
}
