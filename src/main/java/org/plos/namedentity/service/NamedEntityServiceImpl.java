package org.plos.namedentity.service;

import java.util.List;

import javax.inject.Inject;

import org.plos.namedentity.api.NedException;
import org.plos.namedentity.persist.NamedEntityDBService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class NamedEntityServiceImpl implements NamedEntityService {

    @Inject private NamedEntityDBService namedEntityDBService; 

    @Override
    public <T> Integer create(T t) {
		return namedEntityDBService.create(t);
    }

    @Override
    public <T> boolean update(T t) {
        return namedEntityDBService.update(t);
    }

    @Override
    public <T> boolean delete(T t) {
        return namedEntityDBService.delete(t);
    }

    @Override
    public <T> T findById(Integer id, Class<T> clazz) {
        T t = namedEntityDBService.findById(id, clazz);
        if (t == null) {
            throw new NedException(String.format(
                "Record not found searching by id (%s)", t.getClass().getName()));
        }
        return t;
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        return namedEntityDBService.findAll(clazz);
    }

    @Override
    public <T> List<T> findByAttribute(T t) {
        return namedEntityDBService.findByAttribute(t);
    }
    
    public NamedEntityDBService getNamedEntityDBService() {
        return namedEntityDBService;
    }
    
    public void setNamedEntityDBService(NamedEntityDBService namedEntityDBService) {
        this.namedEntityDBService = namedEntityDBService;
    }
}
