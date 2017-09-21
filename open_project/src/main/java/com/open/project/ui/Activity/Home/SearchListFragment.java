package com.open.project.ui.Activity.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.open.project.R;
import com.open.project.base.BaseFragment;
import com.open.project.MainFragmentActivity;
import com.open.project.ui.Activity.AdvanceSearch.AdvanceSearchFragment;
import com.open.project.ui.Activity.SlidePage.SlidingTabLayout;
import com.open.project.ui.Model.Adapter.ViewPagerAdapter;
import com.open.project.ui.Model.LatLong;

import com.open.project.ui.Model.Receive.LoginReceive;

import com.open.project.ui.Realm.Cached.CachedResult;
import com.open.project.ui.Realm.RealmObjectController;
/*import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
*/

import com.google.gson.Gson;


import com.open.project.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * This class allow user to log in the app
 */
public class SearchListFragment extends BaseFragment {

    //@Inject
    //HomePresen presenter;

    //@Inject
    //Bus bus;

    @Bind(R.id.txtTest)
    TextView txtTest;

    public static SearchListFragment newInstance(String listType) {

        SearchListFragment fragment = new SearchListFragment();
        Bundle args = new Bundle();
        args.putString("LIST_TYPE", listType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MainApplication.component(getActivity()).inject(this);
        RealmObjectController.clearCachedResult(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View main = inflater.inflate(R.layout.search_list, container, false);
        ButterKnife.bind(this, main);

        Bundle bundle = getArguments();
        //txtTest.setText(bundle.getString("LIST_TYPE"));

        return main;
    }

    // Declare a variable for the cluster manager.


    @Override
    public void onResume() {
        super.onResume();
        //presenter.onResume();
        //bus.register(this);
        ///mapView.onResume();

        RealmResults<CachedResult> result = RealmObjectController.getCachedResult(MainFragmentActivity.getContext());
        if (result.size() > 0) {

            Gson gson = new Gson();
            if (result.get(0).getCachedAPI() != null) {
                if (result.get(0).getCachedAPI().equals("UserLogin")) {
                    Log.e("CachedData", result.get(0).getCachedResult().toString());
                    LoginReceive obj = gson.fromJson(result.get(0).getCachedResult(), LoginReceive.class);
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // presenter.onPause();
        // mapView.onPause();
        //bus.unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
