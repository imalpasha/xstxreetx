package com.open.project.ui.Activity.Home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.open.project.MainFragmentActivity;
import com.open.project.R;
import butterknife.ButterKnife;

/**
 * This is activity class for LoginFragment
 */
public class HomeActivity extends MainFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, HomeFragment.newInstance()).commit();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
