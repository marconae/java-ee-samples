package de.nae.plumbing;

import java.util.List;

public interface RepositoryService {

    <T> void persist(T entity);

    <T> List<T> findAll(Class<T> entityClass);
}
