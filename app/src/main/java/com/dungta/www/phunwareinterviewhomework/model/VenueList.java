package com.dungta.www.phunwareinterviewhomework.model;

import android.content.Context;

import com.dungta.www.phunwareinterviewhomework.RestClient;
import com.dungta.www.phunwareinterviewhomework.VenuesAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Singleton class to store list of venues retrieved from provided JSON feed.
 */
public class VenueList {
    private static ArrayList<Venue> mVenues;
    private static VenueList sVenueList;
    private Venue mSelectedVenue;
    private RestClient mRestClient;

    private Context mAppContext;

    /**
     * Instantiates new VenueList object, or retrieves instance if exist already.
     * Context param provided to make sure list is same.
     *
     * @param c context object to
     * @return
     */
    public static VenueList get(Context c) {
        if (sVenueList == null) {
            sVenueList = new VenueList(c.getApplicationContext());
        }
        return sVenueList;
    }

    /**
     * Private constructor because singleton. Gets context
     *
     * @param appContext
     */
    private VenueList(Context appContext) {
        mAppContext = appContext;
        mRestClient = new RestClient();
    }

    public ArrayList<Venue> getVenues() {
        return mVenues;
    }

    /**
     * Gets Venue object from ArrayList according to provided Venue ID
     *
     * @param id unique ID of Venue object
     * @return Venue object that matches the ID param
     */
    public Venue getVenue(long id) {
        for (Venue v : mVenues) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * Updates Venue ArrayList with newly provided ArrayList
     *
     * @param updatedVenues ArrayList with possibly updated values
     *                      TODO: Probably should make this smarter later
     */
    public void updateVenues(ArrayList <Venue> updatedVenues) {
       mVenues = updatedVenues;
    }

    public RestClient getRestClient() {
        return mRestClient;
    }

    public void setSelectedVenue(Venue selectedVenue) {
        mSelectedVenue = selectedVenue;
    }

    public Venue getSelectedVenue() {
        return mSelectedVenue;
    }
}
