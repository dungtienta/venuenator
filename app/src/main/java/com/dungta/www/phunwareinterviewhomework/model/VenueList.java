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
 * Created by Dung on 6/11/2015.
 */
public class VenueList {
    private static ArrayList<Venue> mVenues;
    private VenuesAPI mApi;
    private static VenueList sVenueList;
    private static RestClient sRestClient;

    private Context mAppContext;

    public static VenueList get(Context c) {
        if (sVenueList == null) {
            sVenueList = new VenueList(c.getApplicationContext());
        }
        return sVenueList;
    }

    private VenueList(Context appContext) {
        mAppContext = appContext;
    }

    public ArrayList<Venue> getVenues() {
        return mVenues;
    }

    public Venue getVenue(long id) {
        for (Venue v : mVenues) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public void updateVenues(ArrayList <Venue> updatedVenues) {
       mVenues = updatedVenues;
    }
}
