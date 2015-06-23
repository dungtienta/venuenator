package com.dungta.www.phunwareinterviewhomework.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.dungta.www.phunwareinterviewhomework.R;
import com.dungta.www.phunwareinterviewhomework.fragment.VenueListFragment;
import com.dungta.www.phunwareinterviewhomework.model.Venue;

/**
 * Abstract class used to host fragments on specified layout
 */
public abstract class SingleFragmentActivity extends AppCompatActivity
    implements VenueListFragment.Callbacks {

    /**
     * Override to return specific fragment to be hosted
     *
     * @return fragment to be hosted
     */
    protected abstract Fragment createFragment();

    /**
     * Override to specify what layout to be used to host fragment
     *
     * @return id layout of fragment's host activity
     */
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    /**
     * Called by system for initial creation of activity. Sets activity view, gets state.
     * Instantiates object of fragment manager to add fragment while activity is running
     * Grabs fragment if exists already (usually from recreation)
     *
     * @param savedInstanceState bundle with information from previous saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        //If fragment isn't created yet, create fragment and add to host activity
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    /**
     * Override method to handle venue object that is selected
     *
     * @param venue object that is selected
     */
    public void onVenueSelected(Venue venue) {}
}
