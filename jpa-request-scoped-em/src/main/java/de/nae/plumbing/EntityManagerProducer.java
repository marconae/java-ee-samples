package de.nae.plumbing;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@RequestScoped
class EntityManagerProducer {

    @PersistenceContext
    private EntityManager entityManager;

    @Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        log.info("EntityManager {}: produce ({})", Thread.currentThread().getId(), entityManager);
        return entityManager;
    }

    /*public void dispose(@Disposes @Default EntityManager entityManager) {
        final boolean isOpen = entityManager.isOpen();

        log.info("EntityManager {}: dispose{} ({})",
                Thread.currentThread().getId(),
                isOpen ? " and closed" : "",
                entityManager);

        if (isOpen) entityManager.close();
    }*/
}
