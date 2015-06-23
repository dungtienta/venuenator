package com.dungta.www.phunwareinterviewhomework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dungta.www.phunwareinterviewhomework.R;
import com.dungta.www.phunwareinterviewhomework.model.Schedule;
import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.dungta.www.phunwareinterviewhomework.model.VenueList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Fragment class to be hosted by either single fragment or
 * master-detail activity. Populates view with information from selected
 * venue object.
 */
public class VenueFragment extends Fragment{
    public static final String EXTRA_VENUE_ID =
            "com.dungta.www.phunwareinterviewhomework.venue_id";

    private View mView;
    private Venue mVenue;
    private Venue mShareVenue;
    private String mImageUrl;
    private LatLng mVenueLocation;

    private TextView mNameTextView;
    private TextView mAddressTextView;
    private ImageView mVenueImageView;
    private TextView mPhoneTextView;
    private View mScheduleList;
    private GoogleMap mMap;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param venueId id of venue selected.
     * @return A new instance of fragment VenueFragment.
     */
    public static VenueFragment newInstance(long venueId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_VENUE_ID, venueId);

        VenueFragment fragment = new VenueFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Method called by system, handles venue object selected. initializes
     * relevant variables used later to populate UI elements.
     *
     * @param savedInstanceState bundle with information from previous saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make sure arguments are passed to fragment
        if (getArguments() != null) {
            //Get id from arguments to correctly get selected venue object
            long venueId = (long) getArguments().getSerializable(EXTRA_VENUE_ID);

            //Set venue information to access later for view population
            mVenue = VenueList.get(getActivity()).getVenue(venueId);
            mVenueLocation = new LatLng(mVenue.getLatitude(), mVenue.getLongitude());

            setHasOptionsMenu(true);
        }
    }

    /**
     * Called by system, inflates views from xml layout definition
     * Populates view elements with venue information
     *
     * @param inflater object used to inflate views in fragment from xml layout
     * @param container parent view fragment ui to attach to.
     * @param savedInstanceState bundle with information from previous saved state
     * @return view inflated and
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_venue, container, false);

        mNameTextView = (TextView) mView.findViewById(R.id.venue_name_textView);
        mNameTextView.setText(mVenue.getName());

        mAddressTextView = (TextView) mView.findViewById(R.id.venue_address_textView);
        if (!mVenue.getAddress().isEmpty()) {
            mAddressTextView.setText(mVenue.getAddress()
                    + ", " + mVenue.getCity() + " " + mVenue.getZip());
        } else {
            mAddressTextView.setVisibility(View.GONE);
        }

        mImageUrl = mVenue.getImageUrl();
        mVenueImageView = (ImageView) mView.findViewById(R.id.venue_image);
        if (mImageUrl.isEmpty()) {
            mVenueImageView.setImageDrawable(getResources()
                    .getDrawable(R.drawable.phunware_hq));
        } else {
            Picasso.with(getActivity()).load(mImageUrl).into(mVenueImageView);
        }

        mMap = ((SupportMapFragment) getChildFragmentManager()
            .findFragmentById(R.id.venue_map)).getMap();
        if (mMap != null) {
            mMap.addMarker(new MarkerOptions().position(mVenueLocation)
                    .title(mVenue.getName())
                    .snippet(mVenue.getAddress()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mVenueLocation, 12.0f));
        }

        mPhoneTextView = (TextView) mView.findViewById(R.id.venue_phone_textView);
        if (mVenue.getPhone() != "") {
            mPhoneTextView.setText("Phone: " + mVenue.getPhone());
        } else {
            mPhoneTextView.setVisibility(View.GONE);
        }

        //Setup schedule logic
        setUpScheduleTextView();

        return mView;
    }

    /**
     * Gets schedule information from Venue object in form of list.
     * Iterates through list and parses DATE information to View object
     * Creates new TextViews and adds to Parent LinearLayout view
     */
    private void setUpScheduleTextView() {
        if (mView != null) {
            mScheduleList = mView.findViewById(R.id.venue_schedule_information);
            for (Schedule s : mVenue.getSchedule()) {
                //Calendar logic
                String startDate = convertLocalReadableTime(stringToDate(s.getStartDate()));
                String endDate = convertLocalReadableTime(stringToDate(s.getEndDate()));

                //Add layout properties to new text view object
                TextView scheduleText = new TextView(getActivity());
                scheduleText.setPadding(16,0,0,8);
                scheduleText.setText(startDate + " to " + endDate);

                ((LinearLayout) mScheduleList).addView(scheduleText);
            }

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
        inflater.inflate(R.menu.menu_venue_single_frag, menu);
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
                shareVenue();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Creates  and starts intent to share name/address of
     * the current venue. Starts createChooser to allow user to select
     * application to handle intent.
     */
    private void shareVenue() {
        mShareVenue = VenueList.get(getActivity()).getSelectedVenue();
        //TODO: Put more extras if necessary, (ex: address -> Google Maps)
        Intent sendIntent = new Intent();
        //Set intent action and contents
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Venue Name: " + mVenue.getName() + "\n" +
                "Venue Address: " + mVenue.getAddress() + ", " +
                mVenue.getCity() + " " + mVenue.getZip());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,
                getResources().getText(R.string.venue_create_chooser_send)));
    }

    /**
     * Converts date String to Date object
     *
     * @param dateString string that is to be converted
     * @return Date object from converted String
     */
    private Date stringToDate(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        try {
            return format.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes in a Date object param and parses to human readable format
     * Returns Date as String in local time
     *
     * @param date object to be parsed
     * @return string object of readable date in local time
     */
    private String convertLocalReadableTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a ");
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }
}
