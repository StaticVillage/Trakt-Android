package com.staticvillage.traktandroid.model.people;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joelparrish on 12/1/16.
 */

public class MovieCrew {
    private List<MovieCrewItem> production;
    private List<MovieCrewItem> art;
    private List<MovieCrewItem> crew;
    @SerializedName("costume & make-up")
    private List<MovieCrewItem> costumeAndMakeup;
    private List<MovieCrewItem> directing;
    private List<MovieCrewItem> writing;
    private List<MovieCrewItem> sound;
    private List<MovieCrewItem> camera;
    private List<MovieCrewItem> lighting;
    private List<MovieCrewItem> visualEffects;
    private List<MovieCrewItem> editing;

    public List<MovieCrewItem> getProduction() {
        return production;
    }

    public void setProduction(List<MovieCrewItem> production) {
        this.production = production;
    }

    public List<MovieCrewItem> getArt() {
        return art;
    }

    public void setArt(List<MovieCrewItem> art) {
        this.art = art;
    }

    public List<MovieCrewItem> getCrew() {
        return crew;
    }

    public void setCrew(List<MovieCrewItem> crew) {
        this.crew = crew;
    }

    public List<MovieCrewItem> getCostumeAndMakeup() {
        return costumeAndMakeup;
    }

    public void setCostumeAndMakeup(List<MovieCrewItem> costumeAndMakeup) {
        this.costumeAndMakeup = costumeAndMakeup;
    }

    public List<MovieCrewItem> getDirecting() {
        return directing;
    }

    public void setDirecting(List<MovieCrewItem> directing) {
        this.directing = directing;
    }

    public List<MovieCrewItem> getWriting() {
        return writing;
    }

    public void setWriting(List<MovieCrewItem> writing) {
        this.writing = writing;
    }

    public List<MovieCrewItem> getSound() {
        return sound;
    }

    public void setSound(List<MovieCrewItem> sound) {
        this.sound = sound;
    }

    public List<MovieCrewItem> getCamera() {
        return camera;
    }

    public void setCamera(List<MovieCrewItem> camera) {
        this.camera = camera;
    }

    public List<MovieCrewItem> getLighting() {
        return lighting;
    }

    public void setLighting(List<MovieCrewItem> lighting) {
        this.lighting = lighting;
    }

    public List<MovieCrewItem> getVisualEffects() {
        return visualEffects;
    }

    public void setVisualEffects(List<MovieCrewItem> visualEffects) {
        this.visualEffects = visualEffects;
    }

    public List<MovieCrewItem> getEditing() {
        return editing;
    }

    public void setEditing(List<MovieCrewItem> editing) {
        this.editing = editing;
    }
}
