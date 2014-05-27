package org.plos.namedentity.service;

import java.util.List;

/* -------------------------------------------------------------------------- */
/*   Named Entity Service Low-Level Core API                                  */
/* -------------------------------------------------------------------------- */

public interface NamedEntityService {

    public <T> Integer create(T t);
    public <T> boolean update(T t);
    public <T> boolean delete(T t);

    public <T> T       findById(Integer id, Class<T> clazz);
    public <T> List<T> findAll        (Class<T> clazz);
    public <T> List<T> findByAttribute(T t);
}
