package com.staticvillage.traktandroid.model.people;

import java.util.List;

/**
 * Created by joelparrish on 12/1/16.
 */

public class MovieCredits {
    private List<MovieCharacter> cast;
    private MovieCrew crew;

    public List<MovieCharacter> getCast() {
        return cast;
    }

    public void setCast(List<MovieCharacter> cast) {
        this.cast = cast;
    }

    public MovieCrew getCrew() {
        return crew;
    }

    public void setCrew(MovieCrew crew) {
        this.crew = crew;
    }
}
