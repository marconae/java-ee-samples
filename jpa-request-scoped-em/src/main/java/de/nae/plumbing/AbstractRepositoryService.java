package de.nae.plumbing;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
abstract class AbstractRepositoryService implements RepositoryService {

    private static AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

    abstract EntityManager getEntityManager();

    @PostConstruct
    private void init() {
        instanceId = INSTANCE_COUNTER.incrementAndGet();
        log.info("RepositoryService {} {}: init, EntityManager {}",
                Thread.currentThread().getId(),
                instanceId,
                getEntityManager());
    }

    @PreDestroy
    private void destroy() {
        log.info("RepositoryService {} {}: destroy, EntityManager {}",
                Thread.currentThread().getId(),
                instanceId,
                getEntityManager());
    }

    private int instanceId;

    @Override
    public <T> void persist(T entity) {
        log.info("Persisting {} via {}", entity, getEntityManager());
        getEntityManager().persist(entity);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        final String query = String.format("SELECT e FROM %s e" , entityClass.getSimpleName());
        return getEntityManager().createQuery(query, entityClass).getResultList();
    }
}
