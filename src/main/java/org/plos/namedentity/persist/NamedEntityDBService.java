package org.plos.namedentity.persist;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface NamedEntityDBService {

    @Transactional public Integer newNamedEntityId(String typeCode);
    @Transactional public <T> Integer create(T t);
    @Transactional public <T> boolean update(T t);
    @Transactional public <T> boolean delete(T t);

    public <T> List<T> findAll(Class<T> clazz);
    public <T> T       findById(Integer id, Class<T> clazz);
    public <T> List<T> findByAttribute(T t);
}
