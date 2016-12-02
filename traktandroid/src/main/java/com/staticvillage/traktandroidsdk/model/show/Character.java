package com.staticvillage.traktandroidsdk.model.show;

import com.staticvillage.traktandroidsdk.model.Movie;
import com.staticvillage.traktandroidsdk.model.Show;

/**
 * Created by joelparrish on 12/1/16.
 */

public class Character {
    private String character;
    private Show show;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
