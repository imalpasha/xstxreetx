package com.open.project.api;

import com.open.project.ui.Model.Receive.LoginReceive;
import com.open.project.ui.Model.Request.LoginRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface ApiService {

    //LOGIN
    @POST("/AuthenticateUser")
    void onRequestToLogin(@Body LoginRequest task, Callback<LoginReceive> callback);

}


