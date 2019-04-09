package de.nae.threads;

import de.nae.business.MovieServiceOne;
import de.nae.business.MovieServiceTwo;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Random;

@Slf4j
@Dependent
@Transactional
public class Job {

    @Inject
    private MovieServiceOne movieServiceOne;

    @Inject
    private MovieServiceTwo movieServiceTwo;

    private static final Random RND = new Random();

    public String work() {
        final String nameOne = String.format("Movie %d", RND.nextInt(100));
        movieServiceOne.createMovie(nameOne);

        final String nameTwo = String.format("Movie %d", RND.nextInt(100));
        movieServiceTwo.createMovie(nameTwo);

        return nameOne;
    }
}