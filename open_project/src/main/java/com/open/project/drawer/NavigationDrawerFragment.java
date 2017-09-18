/* Will change accordingly */
package com.open.project.drawer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.open.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the
     * user manually expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    ArrayList<DrawerItem> itemList = new ArrayList<DrawerItem>();
    private NavigationDrawerAdapter drawerAdapter;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated
        // awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition);
        Log.e("MASUK SINI","OK");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of
        // actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDrawerListView = (ListView) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView.setVerticalScrollBarEnabled(false);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position, (DrawerItem) parent.getItemAtPosition(position));
            }
        });

		/* Initiate NavigationDrawer Item */
        //menuTop();
        //menuCenter(DEPARTMENT1);
        //menuBottom(htmlListString);

        drawerAdapter = new NavigationDrawerAdapter(getActivity(), this);
        drawerAdapter.setItems(itemList);
        mDrawerListView.setAdapter(drawerAdapter);
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

        return mDrawerListView;
    }

    public void menuTop() {
        itemList.clear();
        itemList = new ArrayList<DrawerItem>();

        DrawerItem vrsm = new DrawerItem();
        vrsm.setId(0);
        vrsm.setTitle(getString(R.string.app_name));
        vrsm.setTag("HEADER");
        vrsm.setLayoutId(NavigationDrawerAdapter.DrawerViewType.HEADER_CLOSEBTN);
        //vrsm.setBackgroundColor(getResources().getColor(R.color.theme1_tab_bg_0));
        itemList.add(vrsm);

        DrawerItem gallery = new DrawerItem();
        gallery.setId(1);
        gallery.setTag("Gallery");
        // d1.setTitle(getString(R.string.action_example));
        gallery.setTitle("Gallery");
        gallery.setLayoutId(NavigationDrawerAdapter.DrawerViewType.MENU);
        itemList.add(gallery);

        DrawerItem sbb = new DrawerItem();
        sbb.setId(2);
        sbb.setTag("SBB");
        // d3.setTitle(getString(R.string.action_example));
        sbb.setTitle("Shop By Brand");
        sbb.setLayoutId(NavigationDrawerAdapter.DrawerViewType.MENU);
        itemList.add(sbb);

    }


    public void addProfile()
    {
        DrawerItem profile = new DrawerItem();
        profile.setId(21);
        profile.setTag("PROFILE");
        // d1.setTitle(getString(R.string.action_example));
        profile.setTitle("PROFILE");
        profile.setLayoutId(NavigationDrawerAdapter.DrawerViewType.DRAWER_PROFILE);
        itemList.add(profile);
    }

    public void menuCenter(String jsonStr) {
        JSONArray json = null;
        try {
            json = new JSONArray(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DrawerItem d0 = new DrawerItem();
        d0.setId(3);
        d0.setTitle("Department");
        d0.setTag("HEADER");
        d0.setLayoutId(NavigationDrawerAdapter.DrawerViewType.HEADER);
        d0.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.charcoal_start));
        itemList.add(d0);
        if(json != null){
            for (int i = 0; i < json.length(); i++) {
                JSONObject row = (JSONObject) json.opt(i);

                DrawerItem dynamicDepartmentList = new DrawerItem();
                dynamicDepartmentList.setTag(row.optInt("departmentId"));
                dynamicDepartmentList.setId(row.optInt("departmentId"));
                dynamicDepartmentList.setLvl(row.optInt("departmentLvl"));
                dynamicDepartmentList.setTitle(row.optString("departmentName"));
                dynamicDepartmentList.setLayoutId(NavigationDrawerAdapter.DrawerViewType.MENU);
                itemList.add(dynamicDepartmentList);
            }
        }

    }

    public void dynamicMenuBottom(String jsonStr) {

        JSONArray json = null;
        try {
            if (jsonStr != null)
                json = new JSONArray(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json != null) {
            for (int i = 0; i < json.length(); i++) {
                JSONObject row = (JSONObject) json.opt(i);

                if (!row.optString("htmlPath").equalsIgnoreCase("")) {
                    DrawerItem dynamicHtmlList = new DrawerItem();
                    dynamicHtmlList.setTag("htmlName");
                    dynamicHtmlList.setId(row.optInt("htmlId"));
                    dynamicHtmlList.setTitle(row.optString("htmlName"));
                    dynamicHtmlList.setPath(row.optString("htmlPath"));
                    dynamicHtmlList.setLayoutId(NavigationDrawerAdapter.DrawerViewType.MENU);
                    itemList.add(dynamicHtmlList);
                }
            }
        }
    }

    public void menuBottom(String htmList) {
        DrawerItem headerMore = new DrawerItem();
        headerMore.setId(11);
        headerMore.setTag("HEADER");
        headerMore.setTitle("Test");
        headerMore.setLayoutId(NavigationDrawerAdapter.DrawerViewType.HEADER);
        headerMore.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.charcoal_start));
        itemList.add(headerMore);


    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation
     * drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
                R.string.app_name, /*
                                         * "open drawer" description for
										 * accessibility
										 */
                R.string.app_name /*
                                         * "close drawer" description for
										 * accessibility
										 */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu(); // calls
                // onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this
                    // flag to
                    // prevent auto-showing
                    // the navigation drawer automatically in the
                    // future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().invalidateOptionsMenu(); // calls
                // onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce
        // them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            // mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position, DrawerItem itemAtPosition) {
        selectItem(position);
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position, itemAtPosition);
        }
    }

    private void selectItem(int position) {
        Log.e("Clicked", "True");
        mCurrentSelectedPosition = position;
        // if (mDrawerListView != null) {
        // mDrawerListView.setItemChecked(position, true);
        // }
        if (mDrawerLayout != null) {
            int layoutId = itemList.get(position).getLayoutId();
            if (layoutId == NavigationDrawerAdapter.DrawerViewType.HEADER || layoutId == NavigationDrawerAdapter.DrawerViewType.HEADER_CLOSEBTN) {
                return;
            }
            closeDrawer();
        }
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
        Log.e("closeDrawer","closeDrawer");

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar.
        // See also
        // showGlobalContextActionBar, which controls the top-left area of the
        // action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {

            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to
     * show the global app 'context', rather than just what's in the current
     * screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        // actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        // actionBar.setTitle(R.string.app_name);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar);
    }

    private ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must
     * implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        // void onNavigationDrawerItemSelected(int position);

        void onNavigationDrawerItemSelected(int position, DrawerItem itemAtPosition);
    }

    public void openDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);

        } else {
            mDrawerLayout.openDrawer(mFragmentContainerView);
           /* Log.e("openDrawer","openDrawer");
            if (session.isLoggedIn()) {
                if (Utils.getDeviceType(getActivity()) == "1") {
                    addProfile();
                }
            }
            */
        }

    }

}
