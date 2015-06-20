package com.dungta.www.phunwareinterviewhomework;

import android.util.Log;

import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Dung on 6/12/2015.
 */
public class RestClient {

    public static final String ENDPOINT =
            "https://s3.amazonaws.com";

    private VenuesAPI api = null;

    public RestClient() {
        Log.d("here", "YOU ARE HERE_1");
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                //.setConverter(new GsonConverter(gson))
                .build();
        Log.d("here", "YOU ARE HERE_2");
        api = adapter.create(VenuesAPI.class);
    }

    public VenuesAPI getVenuesAPI() {
        return api;
    }

}
