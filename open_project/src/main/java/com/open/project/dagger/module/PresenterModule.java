package com.open.project.dagger.module;

import android.content.Context;

import com.open.project.ui.Presenter.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    LoginPresenter provideLoginPresenter(Context context) {
        return new LoginPresenter(context);
    }

}

