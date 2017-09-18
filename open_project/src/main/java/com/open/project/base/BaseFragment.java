package com.open.project.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.open.project.MainController;
import com.open.project.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseFragment extends Fragment {

    public com.open.project.base.AQuery aq;
    static MediaPlayer backgroundMP;
    static int currentLength = 0;
    private static ProgressDialog progressDialog;

    public static void comic_backgroundMusic(Activity act) {
        if (backgroundMP != null) {
            backgroundMP.stop();
        }
        backgroundMP = MediaPlayer.create(act, R.raw.payon);
        backgroundMP.setLooping(true);
        backgroundMP.setVolume(80, 80);
        backgroundMP.start();
    }

    public static void comic_pauseBackgroundMusic() {
        if (backgroundMP != null) {
            backgroundMP.pause();
            currentLength = backgroundMP.getCurrentPosition();
        }
    }

    public static void comic_playBackgroundMusic(Activity act) {
        if (backgroundMP != null) {
            backgroundMP.stop();
            if (currentLength != 0) {
                backgroundMP = MediaPlayer.create(act, R.raw.payon);
                backgroundMP.setLooping(true);
                backgroundMP.setVolume(80, 80);
                backgroundMP.seekTo(currentLength);
                backgroundMP.start();
            } else {
                backgroundMP = MediaPlayer.create(act, R.raw.payon);
                backgroundMP.setLooping(true);
                backgroundMP.setVolume(80, 80);
                backgroundMP.start();
            }
        } else {
            backgroundMP = MediaPlayer.create(act, R.raw.payon);
            backgroundMP.setLooping(true);
            backgroundMP.setVolume(80, 80);
            backgroundMP.start();
        }
    }

    public static void setAlertNotification(Activity act) {

        dismissLoading();
        if (MainController.connectionAvailable(act)) {
            setAlertDialog(act, "Title Error","Error");
        } else {
            setAlertDialog(act, "Title Error", "No Connection");
        }
    }

    public static void dismissLoading() {

        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                Log.e("Dismiss", "Y");
            }
        }
        Log.e("Dismiss", "N");
    }

    public static void initiateLoading(Activity act) {

        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {

            }
        }

        progressDialog = new ProgressDialog(act);
        progressDialog.show();
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

    }

    public static void setAlertDialog(final Activity act, String msg, String title) {

        if (act != null) {
            if (!act.isFinishing()) {

                new SweetAlertDialog(act, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(title)
                        .setContentText(msg)
                        .show();

            }
        }
    }

    public void hideKeyboard() {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }

    }
}
