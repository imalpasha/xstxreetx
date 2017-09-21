package com.open.project.ui.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Dell on 9/18/2017.
 */

public class LatLong implements ClusterItem {
    private LatLng mPosition;
    private String mTitle;
    private String mSnippet;

    public LatLong(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public LatLong(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
