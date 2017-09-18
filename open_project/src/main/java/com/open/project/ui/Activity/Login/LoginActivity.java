package com.open.project.ui.Activity.Login;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.open.project.MainFragmentActivity;
import com.open.project.R;
import com.open.project.ui.Activity.FragmentContainerActivity;

import butterknife.ButterKnife;

/**
 * This is activity class for LoginFragment
 */
public class LoginActivity extends MainFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, LoginFragment.newInstance()).commit();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
