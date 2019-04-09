package de.nae.business;

import de.nae.entity.Movie;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
class MovieFactory {

    static Movie createMovie(@NonNull final String name) {
        final Movie movie = new Movie();
        movie.setName(name);
        movie.setReleaseDate(LocalDate.now());
        return movie;
    }
}
