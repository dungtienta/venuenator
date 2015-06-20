package com.dungta.www.phunwareinterviewhomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.dungta.www.phunwareinterviewhomework.model.VenueList;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dung on 6/10/2015.
 */
public class VenueSplashScreen extends Activity{

    private VenuesAPI mApi;
    private RestClient mRestClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Instantiate VenueList singleton to grab initial list of venues
        requestData();
    }

    private void requestData() {
        mRestClient = new RestClient();
        mApi = mRestClient.getVenuesAPI();

        mApi.getFeed(new Callback<ArrayList<Venue>>() {
            @Override
            public void success(ArrayList<Venue> venues, Response response) {
                VenueList.get(getApplication()).updateVenues(venues);

                Intent i = new Intent(getApplication(), VenueListActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
