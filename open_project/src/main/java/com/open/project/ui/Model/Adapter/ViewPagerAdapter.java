package com.open.project.ui.Model.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.open.project.ui.Activity.Home.SearchListFragment;
import com.open.project.ui.Activity.Login.LoginFragment;

/**
 * Created by hp1 on 21-01-2015.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    String status;
    Context context;
    Activity act;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, int myImageList[], int mNumbOfTabsumb, Activity act) {
        super(fm);

        this.Titles = myImageList;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.act = act;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            SearchListFragment tab1 = new SearchListFragment();
            return tab1;

        } else {
            SearchListFragment tab2 = new SearchListFragment();
            return tab2;

        }
    }

    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return act.getString(Titles[position]);
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}