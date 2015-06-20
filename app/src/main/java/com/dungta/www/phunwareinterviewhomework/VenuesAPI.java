package com.dungta.www.phunwareinterviewhomework;

import com.dungta.www.phunwareinterviewhomework.model.Venue;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Dung on 6/10/2015.
 */
public interface VenuesAPI {

    @GET("/jon-hancock-phunware/nflapi-static.json")
    public void getFeed(Callback<ArrayList<Venue>> response);
}
