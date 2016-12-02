package com.staticvillage.traktandroidsdk.model.movie;

import com.staticvillage.traktandroidsdk.model.movie.Character;

import java.util.List;

/**
 * Created by joelparrish on 12/1/16.
 */

public class Credits {
    private List<Character> cast;
    private Crew crew;

    public List<Character> getCast() {
        return cast;
    }

    public void setCast(List<Character> cast) {
        this.cast = cast;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }
}
