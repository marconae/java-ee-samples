package de.nae.plumbing;

import de.nae.business.MovieServiceOne;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Singleton
@Startup
public class StarterService {

    @Inject
    private MovieServiceOne movieServiceOne;

    @PostConstruct
    private void init() {
        movieServiceOne.createMovie("First Movie");
    }

}
