package com.staticvillage.traktandroidsdk.model.movie;

import java.util.List;

/**
 * Created by joelparrish on 12/1/16.
 */

public class Crew {
    private List<CrewEntry> producing;
    private List<CrewEntry> writing;
    private List<CrewEntry> directing;

    public List<CrewEntry> getProducing() {
        return producing;
    }

    public void setProducing(List<CrewEntry> producing) {
        this.producing = producing;
    }

    public List<CrewEntry> getWriting() {
        return writing;
    }

    public void setWriting(List<CrewEntry> writing) {
        this.writing = writing;
    }

    public List<CrewEntry> getDirecting() {
        return directing;
    }

    public void setDirecting(List<CrewEntry> directing) {
        this.directing = directing;
    }
}
