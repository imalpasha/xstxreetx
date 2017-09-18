package com.open.project.api;

import android.content.Context;

import com.google.gson.Gson;
import com.open.project.application.MainApplication;
import com.open.project.MainFragmentActivity;
import com.open.project.base.BaseFragment;
import com.open.project.ui.Model.Receive.LoginReceive;
import com.open.project.ui.Model.Request.LoginRequest;
import com.open.project.ui.Realm.RealmObjectController;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ApiRequestHandler {

    @Inject
    ApiService apiService;

    @Inject
    Bus bus;

    public ApiRequestHandler(Context context) {
        MainApplication.get(context).getNetComponent().inject(this);
    }


    @Subscribe
    public void onLoginRequest(final LoginRequest event) {

        apiService.onRequestToLogin(event, new Callback<LoginReceive>() {

            @Override
            public void success(LoginReceive rhymesResponse, Response response) {

                if (rhymesResponse != null) {
                    bus.post(new LoginReceive(rhymesResponse));
                    RealmObjectController.cachedResult(MainFragmentActivity.getContext(), (new Gson()).toJson(rhymesResponse), "UserLogin");
                } else {
                    BaseFragment.setAlertNotification(MainFragmentActivity.getContext());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                BaseFragment.setAlertNotification(MainFragmentActivity.getContext());
            }

        });
    }
    /*------------------------------------------------------------------------------*/
    /*This method is to check Force Update status*/

   /* @Subscribe
    public void onLoginInfoRequest(final LoginInfoRequest event) {

        apiService.onLoginInfoRequest(event, new Callback<LoginInfoReceive>() {

            @Override
            public void success(LoginInfoReceive retroResponse, Response response) {

                if (retroResponse != null) {
                    bus.post(new LoginInfoReceive(retroResponse));
                } else {
                    BaseFragment.setAlertNotification(MainFragmentActivity.getContext());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                BaseFragment.setAlertNotification(MainFragmentActivity.getContext());
            }
        });
    }*/


}
