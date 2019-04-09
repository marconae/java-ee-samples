package de.nae.rest;

import de.nae.business.MovieServiceOne;
import de.nae.business.MovieServiceTwo;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("movie")
@Slf4j
public class MovieResource {

    @Inject
    private MovieServiceOne movieServiceOne;

    @Inject
    private MovieServiceTwo movieServiceTwo;

    @GET
    public String createMovies() {
        movieServiceOne.createMovie("Titanic");
        movieServiceTwo.createMovie("Rocky");
        return "Pong";
    }

}
