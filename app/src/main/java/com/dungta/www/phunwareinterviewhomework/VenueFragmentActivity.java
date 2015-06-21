package com.dungta.www.phunwareinterviewhomework;

import android.support.v4.app.Fragment;

/**
 * Activity class subclass of SingleFragmentActivity to host Venue Fragment
 */
public class VenueFragmentActivity extends SingleFragmentActivity {

    /**
     * Override SingleFragmentActivity's createFragment() method to specify
     * Venue fragment to host
     *
     * @return fragment venue fragment to be hosted
     */
    @Override
    protected Fragment createFragment() {
        //Get long ID of venue selected
        long venueID = (long) getIntent()
                .getSerializableExtra(VenueFragment.EXTRA_VENUE_ID);

        //Returns venue fragment, pass in venue ID param to ensure correct venue object
        return VenueFragment.newInstance(venueID);
    }
}
