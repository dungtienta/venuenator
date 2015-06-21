package com.dungta.www.phunwareinterviewhomework;

import com.dungta.www.phunwareinterviewhomework.model.ScheduleItem;
import com.dungta.www.phunwareinterviewhomework.model.VenueDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Class creates client to handle rest services through retrofit
 * libraries' interface.
 */
public class RestClient {

    public static final String ENDPOINT =
            "https://s3.amazonaws.com";

    private VenuesAPI api = null;

    /**
     * Constructor creates RestAdapter and Gson builder object for JSON mapping to POJO
     * RestAdapter used to create api object to query rest services.
     */
    public RestClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ScheduleItem.class, new VenueDeserializer())
                .create();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .build();
        api = adapter.create(VenuesAPI.class);
    }

    public VenuesAPI getVenuesAPI() {
        return api;
    }

}
