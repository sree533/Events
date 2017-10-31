package com.app.eventsproject.restapi.api;


import com.app.eventsproject.restapi.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface to have all API calls.
 */
public interface EventsAPI {

    @GET("events")
    Call<List<Event>> getEvents();

}
