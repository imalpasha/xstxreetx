package com.open.project.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.open.project.MainFragmentActivity;
import com.open.project.R;
import com.open.project.utils.App;
import com.open.project.ui.Realm.RealmObjectController;

import io.realm.Realm;
import io.realm.RealmResults;

public class BaseFragmentActivity extends FragmentActivity {

    public static String appStatus;
    public com.open.project.base.AQuery aq;
    public static Boolean appForeground;

    public static void setAppStatus(String status) {
        appStatus = status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        aq = new com.open.project.base.AQuery(this);

        try {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setCustomView(R.layout.actionbar);
            View actionBarView = actionBar.getCustomView();
            aq.recycle(actionBarView);
            aq.id(R.id.title).typeface(Typeface.createFromAsset(getAssets(), App.FONT_HELVETICA_NEUE)).textSize(22).textColor(Color.WHITE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*Tab bar*/
    public void setActionbar_home() {

        View actionBarView = getActionBar().getCustomView();
        aq.recycle(actionBarView);

    }

    /*Tab : other than Home*/
    public void setActionbar_normal_title(String title) {

        View actionBarView = getActionBar().getCustomView();
        aq.recycle(actionBarView);

    }

    public void setActionbar_normal_title_from_tab(String title, String pts) {

        View actionBarView = getActionBar().getCustomView();
        aq.recycle(actionBarView);

    }




    /*Normal with back*/
    public void setActionbar_normal_with_back(String title) {

        View actionBarView = getActionBar().getCustomView();
        aq.recycle(actionBarView);

    }

    /*Push Notifications*/
    public void setActionbar_with_point_only(String title) {

        View actionBarView = getActionBar().getCustomView();
        aq.recycle(actionBarView);

    }

    /*Normal with back for booking*/
    public void setActionbar_normal_with_back_booking(String title) {

        View actionBarView = getActionBar().getCustomView();
        aq.recycle(actionBarView);

    }

    /*Profile My BIG Points*/
    public void setActionbar_with_inbox_only(String title) {

        View actionBarView = getActionBar().getCustomView();
        aq.recycle(actionBarView);

    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);

        aq.id(R.id.title).visible();
        aq.id(R.id.title).text(titleId);

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }


    @Override
    public void finish() {
        super.finish();
        System.gc();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.gc();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {

        super.onResume();
        MainFragmentActivity.setContext(this);
    }

}
