package com.open.project.ui.Presenter;

import android.content.Context;

import com.open.project.api.ApiRequestHandler;
import com.open.project.application.MainApplication;

import com.open.project.ui.Model.Request.LoginRequest;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public class LoginPresenter {

    @Inject
    ApiRequestHandler apiRequestHandler;

    @Inject
    Bus bus;

    public LoginPresenter(Context context) {
        MainApplication.component(context).inject(this);
    }

    public void onResume() {
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
    }

    public void onLogin(LoginRequest data) {
        apiRequestHandler.onLoginRequest(data);
    }


}
