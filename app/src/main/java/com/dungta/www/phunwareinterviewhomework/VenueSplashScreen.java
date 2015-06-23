package com.dungta.www.phunwareinterviewhomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dungta.www.phunwareinterviewhomework.model.Schedule;
import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.dungta.www.phunwareinterviewhomework.model.VenueList;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Activity class used as launcher for application.
 * Displays splash screen while initializing singleton
 * Starts VenuesList singleton and queries initial data set.
 */
public class VenueSplashScreen extends Activity{

    private VenuesAPI mApi;
    private RestClient mRestClient;

    /**
     * Method called by system, sets activity's layout
     * Invokes requestData method to initialize app data
     *
     * @param savedInstanceState if fragment is recreated, this object stores state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestData();
    }

    /**
     * Creates a new RestClient and API instance.
     * Invokes interface method to query GET method on restful service
     *
     * On success, updates VenueList singleton's list of venues and
     * transitions user to main application activity.
     */
    private void requestData() {
        mRestClient = new RestClient();
        mApi = mRestClient.getVenuesAPI();

        //Call API interface's getFeed() method to get json info
        mApi.getFeed(new Callback<ArrayList<Venue>>() {
            @Override
            public void success(ArrayList<Venue> venues, Response response) {
                VenueList.get(getApplication()).updateVenues(venues);
                test();
                Intent i = new Intent(getApplication(), VenueListActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                //Display toast to notify failure occurred
                showToast(R.string.venue_refresh_fail);
            }
        });
    }

    /**
     * Displays a new toast instance
     * with the text provided by the param string id
     *
     * @param toastText id of string resource describing action occurred
     */
    private void showToast(int toastText) {
        Toast toast = Toast
                .makeText(this, toastText, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void test() {
        for(Venue v : VenueList.get(getApplication()).getVenues()) {
            Log.d("something", "more: " + v.getName());
            List<Schedule> test1 = v.getSchedule();
            for (Schedule s : test1) {
                Log.d("SCHEDULE DATE: ", "DATE: " + s.getEndDate());
            }
        }
    }
}
