package com.dungta.www.phunwareinterviewhomework;

import android.app.Activity;
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

import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.dungta.www.phunwareinterviewhomework.model.VenueList;

import java.util.ArrayList;

/**
 * Created by Dung on 6/10/2015.
 */
public class VenueListFragment extends ListFragment {
    private ArrayList<Venue> mVenues;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onVenueSelected(Venue venue);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVenues = VenueList.get(getActivity()).getVenues();
        VenueAdapter adapter = new VenueAdapter(mVenues);
        setListAdapter(adapter);
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Venue venue = ((VenueAdapter)getListAdapter()).getItem(position);
        mCallbacks.onVenueSelected(venue);
    }

    private class VenueAdapter extends ArrayAdapter<Venue> {

        public VenueAdapter(ArrayList<Venue> venues) {
            super(getActivity(), 0, venues);
        }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_venue_description, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            //case R.id.edit_item:
                // do s.th.
              //  return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
