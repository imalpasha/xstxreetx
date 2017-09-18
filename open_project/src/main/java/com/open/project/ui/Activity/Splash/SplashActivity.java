package com.open.project.ui.Activity.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.open.project.MainFragmentActivity;
import com.open.project.ui.Activity.Login.LoginActivity;

/**
 * This is activity class for SplashFragment
 */
public class SplashActivity extends MainFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent start = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(start);
                finish();
            }
        }, 1500);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
