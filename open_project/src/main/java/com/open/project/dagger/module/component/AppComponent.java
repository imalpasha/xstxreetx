package com.open.project.dagger.module.component;

import com.open.project.AppModule;
import com.open.project.api.ApiRequestHandler;
import com.open.project.api.ApiService;
import com.open.project.dagger.module.NetModule;
import com.open.project.dagger.module.PresenterModule;
import com.open.project.ui.Activity.Login.LoginFragment;
import com.open.project.ui.Presenter.LoginPresenter;
import com.squareup.otto.Bus;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, PresenterModule.class})
public interface AppComponent {

    Bus bus();

    ApiService apiService();

    void inject(LoginFragment loginFragment);
    void inject(ApiRequestHandler frag);
    void inject(LoginPresenter loginPresenter);

}



