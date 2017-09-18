package com.open.project.dagger.module;

import android.content.Context;
import android.util.Log;

import com.open.project.api.ApiEndpoint;
import com.open.project.api.ApiRequestHandler;
import com.open.project.api.ApiService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Module
public class NetModule {

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return new ApiEndpoint();
    }

    @Provides
    @Singleton
    ApiService provideApiService(RequestInterceptor requestInterceptor, Endpoint endpoint) {

        int MAX_IDLE_CONNECTIONS = 30 * 60 * 1000;
        int KEEP_ALIVE_DURATION_MS = 3 * 60 * 1000;

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(120, TimeUnit.SECONDS);

        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new RestAdapter.Log() {
                    public void log(String msg) {
                        Log.i("retrofit", msg);
                    }
                })
                .build()
                .create(ApiService.class);
    }

    /*

    *  */

    @Provides
    @Singleton
    ApiRequestHandler apiRequestHandler(Context context) {
        return new ApiRequestHandler(context);
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

    @Provides
    @Singleton
    RequestInterceptor provideRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addHeader("Content-Type", "application/json");
            }
        };
    }

}

