package org.plos.namedentity.service;

import java.util.Collection;
import javax.inject.Inject;

import org.plos.namedentity.api.TypedescriptionsDTO;
import org.plos.namedentity.persist.NamedEntityDBService;

public class NamedEntityServiceImpl implements NamedEntityService {

    @Inject private NamedEntityDBService namedEntityDBService; 

    @Override
    public TypedescriptionsDTO create(TypedescriptionsDTO typeDescription) {
		Integer pk = namedEntityDBService.create(typeDescription);
		return namedEntityDBService.findById(pk, TypedescriptionsDTO.class);
    }

    @Override
    public TypedescriptionsDTO update(TypedescriptionsDTO typeDescription) {
        namedEntityDBService.update(typeDescription);
		return namedEntityDBService.findById(typeDescription.getTypeid(), TypedescriptionsDTO.class);
    }

    @Override
    public boolean delete(TypedescriptionsDTO typeDescription) {
        return namedEntityDBService.delete(typeDescription);
    }

    @Override
    public TypedescriptionsDTO findTypedescriptionById(Integer typeId) {
		return namedEntityDBService.findById(typeId, TypedescriptionsDTO.class);
    }

    @Override
    public Collection<TypedescriptionsDTO> getTypedescriptions() {
        return namedEntityDBService.findAll(TypedescriptionsDTO.class);
    }
    
    public NamedEntityDBService getNamedEntityDBService() {
        return namedEntityDBService;
    }
    
    public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
        this.namedEntityDBService = namedEntityDBService;
    }
}
