package org.plos.namedentity.persist;

import java.util.List;

public interface NamedEntityDBService {

    //TODO - replace typecode string with enum?
    public Integer newNamedEntityId(String typeCode);

    public <T> Integer create(T t);
    public <T> boolean update(T t);
    public <T> boolean delete(T t);

    public <T> List<T> findAll(Class<T> clazz);
    public <T> T       findById(Integer id, Class<T> clazz);
    public <T> List<T> findByAttribute(T t);
}
