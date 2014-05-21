package org.plos.namedentity.service;

import java.util.Collection;

import org.plos.namedentity.api.TypedescriptionsDTO;

public interface NamedEntityService {

    public <T> Integer create(T t);
    public <T> boolean update(T t);
    public <T> boolean delete(T t);

    public <T> T findById(Integer id, Class<T> clazz);
    public <T> Collection<T> findAll(Class<T> clazz);
}
