package com.app.eventsproject.restapi.model;

import com.google.gson.annotations.SerializedName;

public class Event {

    private int id;

    @SerializedName("type")
    private String eventType;

    private Actor actor;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
