package com.dungta.www.phunwareinterviewhomework.util;

import com.dungta.www.phunwareinterviewhomework.model.Venue;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface used by Retrofit created from REST API
 */
public interface VenuesAPI {

    /**
     * Creates request method by providing HTTP annotation and relative URL
     *
     * @param response query parameter
     */
    @GET("/jon-hancock-phunware/nflapi-static.json")
    public void getFeed(Callback<ArrayList<Venue>> response);
}
