package com.app.eventsproject.restapi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class contains the base url and helper methods to create retrofit instance
 */
public class BaseURL {

    private static final String BASE_URL = "https://api.github.com";

    private static Retrofit getBaseUrl() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static EventsAPI getAPI() {
        return BaseURL.getBaseUrl().create(EventsAPI.class);
    }
}
