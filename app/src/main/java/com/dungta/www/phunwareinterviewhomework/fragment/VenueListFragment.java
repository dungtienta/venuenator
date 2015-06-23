package com.dungta.www.phunwareinterviewhomework.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dungta.www.phunwareinterviewhomework.R;
import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.dungta.www.phunwareinterviewhomework.model.VenueList;
import com.dungta.www.phunwareinterviewhomework.util.RestClient;
import com.dungta.www.phunwareinterviewhomework.util.VenuesAPI;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * ListFragment class displays list of Venue object's names and address.
 * Handles action when Venue is chosen from list, communicates with host activity to display
 * new fragment of the Venue object is chosen.
 */
public class VenueListFragment extends ListFragment {
    private ArrayList<Venue> mVenues;
    private Venue mShareVenue;
    private Callbacks mCallbacks;
    private RestClient mRestClient;
    private VenuesAPI mApi;

    /**
     * Declares actions that the hosting activity needs to implement.
     * Hosting activity needs to implement this interface to get the correct venue
     */
    public interface Callbacks {
        void onVenueSelected(Venue venue);
    }

    /**
     * Called by system when first attached to its activity
     * attached activity is casted to Callbacks interface,
     * therefore hosting activity must implement callback. *Creates dependency
     *
     * @param activity hosting activity, attached to
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    /**
     * Called by system when fragment is detached from hosting activity
     * Callbacks interface is set to null because its not certain activity still exists
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    /**
     * Called by system for creation of fragment. Gets list of venues from
     * VenuesList singleton and sets adapter for list fragment
     *
     * @param savedInstanceState bundle with information from previous saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVenues = VenueList.get(getActivity()).getVenues();
        VenueAdapter adapter = new VenueAdapter(mVenues);
        setListAdapter(adapter);
        setHasOptionsMenu(true);
    }

    /**
     * Handles when an item in the list is selected. Gets Venue object at
     * given selected position. Communicates with activity to handle selected
     * Venue object.
     *
     * @param l ListView of where the click occurred
     * @param v view selected within ListView l
     * @param position position of the view selected in ListView
     * @param id row id
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Venue venue = ((VenueAdapter)getListAdapter()).getItem(position);
        VenueList.get(getActivity()).setSelectedVenue(venue);
        mCallbacks.onVenueSelected(venue);
    }

    /**
     * Subclass of ArrayAdapter to return custom view by overriding getView() method.
     * Returns custom view showing Venue information (name/address).
     */
    private class VenueAdapter extends ArrayAdapter<Venue> {

        /**
         * Constructor
         *
         * @param venues objects passed into adapter
         */
        public VenueAdapter(ArrayList<Venue> venues) {
            super(getActivity(), 0, venues);
        }

        /**
         * Gets a view to display data for one position in given data set. Inflates
         * View from XML layout. Wire up view information
         *
         * @param position position of item within adapter's data set
         * @param convertView old view to reuse
         * @param parent parent returned view will be attached to
         * @return convertView View object with wired up information
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_venue, null);
            }

            //Configure list item view
            Venue v = getItem(position);

            TextView nameTextView =
                    (TextView) convertView.findViewById(R.id.venue_list_item_nameTextView);
            nameTextView.setText(v.getName());

            TextView addressTextView =
                    (TextView) convertView.findViewById(R.id.venue_list_item_addressTextView);
            if (v.getAddress().isEmpty()) {
                addressTextView.setText("Address Unknown");
            } else {
                addressTextView.setText(v.getAddress());
            }

            return convertView;
        }
    }

    /**
     * Called by system to inflate options menu
     *
     * @param menu menu which items are placed
     * @param inflater class instance used to instantiate xml to menu objects
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_venue_description, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Called by the system when an item from the options
     * menu is selected. Param item determines which item was selected by
     * a unique ID.
     *
     * @param item option item selected
     * @return boolean if item selected was handled successfully
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                /** TODO: This should not work in case master-detail is not shown because
                 *  if this is a list only, how do we know what venue object is selected?
                 */
                shareVenue();
                return true;
            case R.id.menu_item_refresh:
                requestData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Displays a new toast instance
     * with the text provided by the param string id
     *
     * @param toastText id of string resource describing action occurred
     */
    private void showToast(int toastText) {
        Toast toast = Toast
                .makeText(getActivity(), toastText, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Gets previously created RestClient from singleton
     * Invokes interface method to query GET method on restful service
     *
     * On success, updates VenueList singleton's list of venue. notify
     * user of success or failure
     */
    private void requestData() {
        mRestClient = VenueList.get(getActivity()).getRestClient();
        mApi = mRestClient.getVenuesAPI();

        mApi.getFeed(new Callback<ArrayList<Venue>>() {
            @Override
            public void success(ArrayList<Venue> venues, Response response) {
                VenueList.get(getActivity()).updateVenues(venues);
                showToast(R.string.venue_refresh_success);
            }

            @Override
            public void failure(RetrofitError error) {
                showToast(R.string.venue_refresh_fail);
            }
        });
    }

    private void shareVenue() {

        mShareVenue = VenueList.get(getActivity()).getSelectedVenue();
        //TODO: Put more extras if necessary, (ex: address -> Google Maps)
        Intent sendIntent = new Intent();
        //Set intent action and contents
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Venue name: " + mShareVenue.getName() + "\n" +
                        "Venue address: " + mShareVenue.getAddress() + ", " +
                        mShareVenue.getCity() + " " + mShareVenue.getZip());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,
                getResources().getText(R.string.venue_create_chooser_send)));
    }
}
