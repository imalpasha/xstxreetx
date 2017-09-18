// This example uses Google Analytics SDK for Android v3.x implementation
package com.open.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tagmanager.InstallReferrerReceiver;

public class TrackingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Pass the intent to other receivers.
        new InstallReferrerReceiver().onReceive(context, intent);
        //handleIntent(context, intent);

        // When you're done, pass the intent to the Google Analytics receiver.asdsad
        //new CampaignTrackingReceiver().onReceive(context, intent);

    }

    // Handle the intent data
    public void handleIntent(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String referrerString = extras.getString("referrer");

    }
}