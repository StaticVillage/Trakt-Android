package com.staticvillage.traktandroid.model.people;

import java.util.List;

/**
 * Created by joelparrish on 12/1/16.
 */

public class ShowCredits {
    private List<ShowCharacter> cast;
    private ShowCrew crew;

    public List<ShowCharacter> getCast() {
        return cast;
    }

    public void setCast(List<ShowCharacter> cast) {
        this.cast = cast;
    }

    public ShowCrew getCrew() {
        return crew;
    }

    public void setCrew(ShowCrew crew) {
        this.crew = crew;
    }
}
