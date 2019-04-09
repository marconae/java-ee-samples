package de.nae.plumbing;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

@Slf4j
class RepositoryServiceProducer {

    @Produces
    @RequestScoped
    @Default
    public RepositoryService produce(EntityManager entityManager) {
        final RepositoryService repositoryService = new AbstractRepositoryService() {
            @Override
            EntityManager getEntityManager() {
                return entityManager;
            }
        };

        log.info("RepoService {}: producing {} with EntityManager {}", Thread.currentThread().getId(), System.identityHashCode(repositoryService), entityManager);
        return repositoryService;
    }

    public void dispose(@Disposes RepositoryService repositoryService) {
        log.info("RepoService {}: disposing", Thread.currentThread().getId(), System.identityHashCode(repositoryService));
    }

}
