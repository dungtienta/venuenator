package com.dungta.www.phunwareinterviewhomework;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dungta.www.phunwareinterviewhomework.model.Venue;

/**
 * Activity class subclassing SingleFragmentActivity, hosts list fragment.
 */
public class VenueListActivity extends SingleFragmentActivity {

    /**
     * Overrides parent class' createFragment() to specify list fragment to host
     *
     * @return fragment list fragment to host
     */
    @Override
    protected Fragment createFragment() {
        return new VenueListFragment();
    }

    /**
     * Overrides parent class' getLayoutResId to specify layout
     *
     * @return id layout id to use
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    /**
     * Determines what to do with object selected from menu
     *
     * @param venue object that is selected
     */
    @Override
    public void onVenueSelected(Venue venue) {
        if (findViewById(R.id.detailFragmentContainer) == null) {
            Intent i = new Intent(this, VenuePagerActivity.class);
            i.putExtra(VenueFragment.EXTRA_VENUE_ID, venue.getId());
            startActivity(i);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment defaultDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment replaceDetail = VenueFragment.newInstance(venue.getId());

            if (defaultDetail != null) {
                ft.remove(defaultDetail);
            }

            ft.add(R.id.detailFragmentContainer, replaceDetail);
            ft.commit();
        }
    }
}
