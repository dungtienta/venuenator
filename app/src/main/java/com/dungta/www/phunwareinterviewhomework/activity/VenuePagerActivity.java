package com.dungta.www.phunwareinterviewhomework.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dungta.www.phunwareinterviewhomework.R;
import com.dungta.www.phunwareinterviewhomework.fragment.VenueFragment;
import com.dungta.www.phunwareinterviewhomework.model.Venue;
import com.dungta.www.phunwareinterviewhomework.model.VenueList;

import java.util.ArrayList;

/**
 * ViewPager activity class, hosts venue fragment
 */
public class VenuePagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Venue> mVenues;

    /**
     * Called by system, creates activity by setting layout and loading saved bundle info.
     * Creates fragment manager and sets adapter to handle fragments and user page turning
     *
     * Keeps track of which Venue object is loaded to pass correct info to new fragment.
     *
     * @param savedInstanceState bundle with information from previous saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mVenues = VenueList.get(this).getVenues();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Venue venue = mVenues.get(position);
                return VenueFragment.newInstance(venue.getId());
            }

            @Override
            public int getCount() {
                return mVenues.size();
            }
        });
        //Set listener for when page changes
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //Unused abstract method, just need to implement for callback interface
            public void onPageScrollStateChanged(int state) {
            }
            //Unused abstract method, just need to implement for callback interface
            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {
            }

            /**
             * Handles when new page is selected, gets position of page and sets page
             * name according to selected venue position
             *
             * @param pos index of page currently being displayed
             */
            public void onPageSelected(int pos) {
                Venue venue = mVenues.get(pos);
                if (venue.getName() != null) {
                    setTitle(venue.getName());
                }
            }
        });

        //Set correct crime when clicked on from the list
        long venueId = (long) getIntent()
                .getSerializableExtra(VenueFragment.EXTRA_VENUE_ID);
        for (int i = 0; i < mVenues.size(); i++) {
            if (mVenues.get(i).getId() == venueId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
