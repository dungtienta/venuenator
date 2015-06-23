package com.dungta.www.phunwareinterviewhomework.util;

import com.dungta.www.phunwareinterviewhomework.util.VenuesAPI;

import retrofit.RestAdapter;

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

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
        api = adapter.create(VenuesAPI.class);
    }

    public VenuesAPI getVenuesAPI() {
        return api;
    }

}
