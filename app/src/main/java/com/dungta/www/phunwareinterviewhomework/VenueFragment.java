package com.dungta.www.phunwareinterviewhomework;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.dungta.www.phunwareinterviewhomework.model.VenueList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class VenueFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String EXTRA_VENUE_ID =
            "com.dungta.www.phunwareinterviewhomework.venue_id";

    public static FragmentManager sFragmentManager;

    private Venue mVenue;
    private String mImageUrl;
    private LatLng mVenueLocation;

    private TextView mNameTextView;
    private TextView mAddressTextView;
    private ImageView mVenueImageView;
    private TextView mPhoneTextView;
    private GoogleMap mMap;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param venueId Parameter 1.
     * @return A new instance of fragment VenueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VenueFragment newInstance(long venueId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_VENUE_ID, venueId);

        VenueFragment fragment = new VenueFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public VenueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            long venueId = (long) getArguments().getSerializable(EXTRA_VENUE_ID);
            sFragmentManager = getFragmentManager();
            mVenue = VenueList.get(getActivity()).getVenue(venueId);
            mVenueLocation = new LatLng(mVenue.getLatitude(), mVenue.getLongitude());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_venue, container, false);

        mNameTextView = (TextView) v.findViewById(R.id.venue_name_textView);
        mNameTextView.setText(mVenue.getName());

        mAddressTextView = (TextView) v.findViewById(R.id.venue_address_textView);
        mAddressTextView.setText(mVenue.getAddress()
                + ", " + mVenue.getCity() + " " + mVenue.getZip());

        //Load image url from venue object into ImageView
        mImageUrl = mVenue.getImageUrl();
        mVenueImageView = (ImageView) v.findViewById(R.id.venue_image);
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

        mPhoneTextView = (TextView) v.findViewById(R.id.venue_phone);
        mPhoneTextView.setText(mVenue.getPhone());

        return v;
    }
}
