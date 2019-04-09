package de.nae.business;

import de.nae.entity.Movie;
import de.nae.plumbing.RepositoryService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Slf4j
@Transactional
public class MovieServiceTwo {

    @Inject
    private RepositoryService repositoryService;

    public void createMovie(String name) {
        final Movie movie = MovieFactory.createMovie(name);
        repositoryService.persist(movie);
    }
}
