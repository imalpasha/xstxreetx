package com.open.project;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;
    private Context context;

    public AppModule(Application application,Context context) {
        mApplication = application;
        this.context = context;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    Context provideContext() {
        return this.context;
    }



}


