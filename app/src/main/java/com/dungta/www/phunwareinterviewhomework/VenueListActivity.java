package com.dungta.www.phunwareinterviewhomework;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dungta.www.phunwareinterviewhomework.model.Venue;

/**
 * Created by Dung on 6/10/2015.
 */
public class VenueListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new VenueListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

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
