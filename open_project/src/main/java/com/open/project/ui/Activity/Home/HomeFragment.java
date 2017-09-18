package com.open.project.ui.Activity.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.open.project.R;
import com.open.project.application.MainApplication;
import com.open.project.base.BaseFragment;
import com.open.project.MainController;
import com.open.project.MainFragmentActivity;
import com.open.project.ui.Activity.AdvanceSearch.AdvanceSearchFragment;
import com.open.project.ui.Presenter.LoginPresenter;
import com.open.project.utils.SharedPrefManager;

import com.open.project.ui.Model.Receive.LoginReceive;
import com.open.project.ui.Model.Request.LoginRequest;

import com.open.project.ui.Realm.Cached.CachedResult;
import com.open.project.ui.Realm.RealmObjectController;
/*import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
*/
import com.google.firebase.analytics.FirebaseAnalytics;

import com.google.gson.Gson;


import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import com.open.project.utils.Utils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * This class allow user to log in the app
 */
public class HomeFragment extends BaseFragment implements OnMapReadyCallback {

    //@Inject
    //HomePresen presenter;

    //@Inject
    //Bus bus;

    /*@Bind(R.id.btnLogin)
    LinearLayout btnLogin;*/

    @Bind(R.id.searchList)
    LinearLayout searchList;

    @Bind(R.id.searchBarBack)
    ImageView searchBarBack;

    @Bind(R.id.searchBar)
    EditText searchBar;

    @Bind(R.id.advancedSearchBtn)
    ImageView advancedSearchBtn;

    @Bind(R.id.searchBarLayout)
    LinearLayout searchBarLayout;


    //MixpanelAPI mixPanel;
    Realm realm;
    private GoogleMap mMap;

    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
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

        final View main = inflater.inflate(R.layout.home, container, false);
        ButterKnife.bind(this, main);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Gets the MapView from the XML layout and creates it
        //mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        searchList.setAlpha(0.0f);
        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean hasfocus) {
                if (hasfocus) {
                    Log.e("HasFocus", "Y");
                    //searchList.setVisibility(View.VISIBLE);
                    //searchList.animate().alpha(1.0f);
                    //view.animate().translationY(view.getHeight());
                    //view.animate().alpha(1.0f);
                    //view.animate().translationY(0);
                    searchList.animate().alpha(1.0f).setDuration(1000);
                    searchBarBack.setVisibility(View.VISIBLE);
                    searchBarLayout.setGravity(0);
                    /*view.animate()
                    .translationY(view.getHeight())
                    .alpha(0.0f)
                    .setDuration(300);*/

                    /*view.animate()
                    .translationY(view.getHeight())
                    .alpha(0.0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setVisibility(View.GONE);
                        }
                    });*/
                } else {
                    Log.e("HasFocus", "N");
                }
            }
        });

        searchBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchList.animate().alpha(0.0f).setDuration(500);
                searchBarBack.setVisibility(View.GONE);
                searchBarLayout.setGravity(Gravity.CENTER);
                searchBar.clearFocus();
                Utils.hideKeyboard(getActivity(), main);
            }
        });

        advancedSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                AdvanceSearchFragment advanceSearchFragment = AdvanceSearchFragment.newInstance();
                advanceSearchFragment.setTargetFragment(HomeFragment.this, 0);
                advanceSearchFragment.show(fm, "advance_search");

            }
        });


        return main;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }

    public void setUpMap() {

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);

        MapsInitializer.initialize(getActivity());
        MarkerOptions marker;

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble("1.000000"), Double.parseDouble("1.000000")), 10);
        mMap.animateCamera(cameraUpdate);

        final HashMap<String, Integer> mMarkers = new HashMap<String, Integer>();

        for (int count = 0; count < 5; count++) {
            final int currentLoop = count;
            marker = new MarkerOptions();
            // marker.snippet(Integer.toString(count));
            Marker mkr = mMap.addMarker(marker.position(new LatLng(Double.parseDouble("1.000000"), Double.parseDouble("2.000000"))).title("Test Store"));
            mMarkers.put(mkr.getId(), count);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {
                    int id = mMarkers.get(marker.getId());
                    //aq.id(R.id.txtAddress).text(storeList.get(id).getAddress());
                    //aq.id(R.id.storeAddressLayout).visible();
                    return false;

                }

            });

            /*aq.id(R.id.navigationIcon).clicked(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String URL = "http://maps.google.com/maps?saddr=&daddr=" + storeList.get(currentLoop).getLatitude() + "," + storeList.get(currentLoop).getLongitude() + "&z=1";
                    Utils.launchNavigator(getActivity(), URL);

                }
            });*/
        }

    }

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
