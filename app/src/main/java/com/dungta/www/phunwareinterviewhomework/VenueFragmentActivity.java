package com.dungta.www.phunwareinterviewhomework;

import android.support.v4.app.Fragment;

/**
 * Created by Dung on 6/18/2015.
 */
public class VenueFragmentActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        long venueID = (long) getIntent()
                .getSerializableExtra(VenueFragment.EXTRA_VENUE_ID);

        return VenueFragment.newInstance(venueID);
    }
}
