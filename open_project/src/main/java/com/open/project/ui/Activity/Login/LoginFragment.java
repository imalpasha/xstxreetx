package com.open.project.ui.Activity.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.open.project.R;
import com.open.project.application.MainApplication;
import com.open.project.base.BaseFragment;
import com.open.project.MainController;
import com.open.project.MainFragmentActivity;
import com.open.project.ui.Activity.Home.HomeActivity;
import com.open.project.ui.Presenter.LoginPresenter;
import com.open.project.utils.SharedPrefManager;

import com.open.project.ui.Model.Receive.LoginReceive;
import com.open.project.ui.Model.Request.LoginRequest;

import com.open.project.ui.Realm.Cached.CachedResult;
import com.open.project.ui.Realm.RealmObjectController;
/*import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
*/
import com.google.firebase.analytics.FirebaseAnalytics;

import com.google.gson.Gson;


import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * This class allow user to log in the app
 */
public class LoginFragment extends BaseFragment implements Validator.ValidationListener {

    @Inject
    LoginPresenter presenter;

    @Inject
    Bus bus;


    @Bind(R.id.continueBox)
    LinearLayout continueBox;

    @NotEmpty(sequence = 1, messageResId = R.string.email_empty)
    @Email(sequence = 2, messageResId = R.string.email_invalid)
    @Order(1)
    @Bind(R.id.txtLoginEmail)
    EditText txtLoginEmail;

    @NotEmpty(sequence = 1, messageResId = R.string.password_empty)
    @Order(2)
    @Bind(R.id.txtLoginPassword)
    EditText txtLoginPassword;

    @Bind(R.id.btnLogin)
    TextView btnLogin;

    private SharedPrefManager pref;

    //MixpanelAPI mixPanel;
    Realm realm;
    Activity act;

    private String country;
    private String languageLanguageCode;
    private String token;
    private String username;
    private String loginEmail;
    private String loginPassword;
    private String tierStatus;
    private String customerNo;
    private String LoginDate;
    private String firstTime;

    private FirebaseAnalytics mFirebaseAnalytics;
    private static Validator mValidator;

    LoginReceive loginReceive;
    LoginReceive loginObj;

    //CallbackManager callbackManager;

    public static LoginFragment newInstance() {

        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = getActivity();

        MainApplication.component(getActivity()).inject(this);
        RealmObjectController.clearCachedResult(getActivity());

        // Validator
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
        mValidator.setValidationMode(Validator.Mode.BURST);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login, container, false);
        ButterKnife.bind(this, view);

        //Set animation
        /*AnimationSet animationSet = new AnimationSet(false);
        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(),R.anim.fadein);
        animationSet.addAnimation(fadeIn);
        loginBox.startAnimation(animationSet);*/

        continueBox.animate().alpha(1.0f).setDuration(1200);
        realm = RealmObjectController.getRealmInstance(act);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard();
                //mValidator.validate();
                Intent startHome = new Intent(getActivity(), HomeActivity.class);
                getActivity().startActivity(startHome);

            }
        });


        return view;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        /* Validation Failed - Toast Error */
        for (ValidationError error : errors) {
            View view = error.getView();
            view.setFocusable(true);
            view.requestFocus();

            /* Split Error Message. Display first sequence only */
            String message = error.getCollatedErrorMessage(act);
            String splitErrorMsg[] = message.split("\\r?\\n");

            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(splitErrorMsg[0]);
            }
        }
    }

    @Override
    public void onValidationSucceeded() {
        /* Validation Success - Start send data to server */
        loginEmail = txtLoginEmail.getText().toString();
        loginPassword = txtLoginPassword.getText().toString();
        loginFromFragment(txtLoginEmail.getText().toString(), txtLoginPassword.getText().toString());
    }

    public void loginFromFragment(String username, String password) {

        initiateLoading(getActivity());

        LoginRequest loginData = new LoginRequest();
        loginData.setUsername(username);
        loginData.setPassword(password);
        loginData.setLanguageCode("en-gb");

        presenter.onLogin(loginData);

    }

    @Subscribe
    public void onLoginSuccess(LoginReceive obj) {

        dismissLoading();
        Boolean status = MainController.getRequestStatus(obj.getStatus(), obj.getMessage(), act);
        if (status) {
            Log.e("Login Success", "Entered");
        } else {
            //dismissLoading();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        bus.register(this);

        RealmResults<CachedResult> result = RealmObjectController.getCachedResult(MainFragmentActivity.getContext());
        if (result.size() > 0) {

            Gson gson = new Gson();
            if (result.get(0).getCachedAPI() != null) {
                if (result.get(0).getCachedAPI().equals("UserLogin")) {
                    Log.e("CachedData", result.get(0).getCachedResult().toString());
                    LoginReceive obj = gson.fromJson(result.get(0).getCachedResult(), LoginReceive.class);
                    onLoginSuccess(obj);

                } else if (result.get(0).getCachedAPI().equals("GetAllData")) {
                    Log.e("CachedData", result.get(0).getCachedResult().toString());
                    loginFromFragment(loginEmail, loginPassword);


                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
        bus.unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
