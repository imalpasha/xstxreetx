package com.open.project.ui.Realm;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.open.project.ui.Model.JSON.UserInfoJSON;
import com.open.project.base.BaseFragment;
import com.open.project.ui.Realm.Cached.CachedResult;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class RealmObjectController extends BaseFragment {


    public static Realm getRealmInstance(Activity act) {

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(act).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        try {
            return Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
    }

    public static Realm getRealmInstanceContext(Context act) {

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(act).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        try {
            return Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
    }

    /*Save user info*/
    public static void saveUserInformation(Context act, String stringfyObj) {

        Realm realm = getRealmInstanceContext(act);

        //clear user info in realm first.
        final RealmResults<UserInfoJSON> result = realm.where(UserInfoJSON.class).findAll();
        realm.beginTransaction();
        result.clear();
        realm.commitTransaction();

        //add new user info in realm
        realm.beginTransaction();
        UserInfoJSON realmObject = realm.createObject(UserInfoJSON.class);
        realmObject.setUserInfo(stringfyObj);
        realm.commitTransaction();
        realm.close();
    }

    //get default result cached (remove later)
    public static RealmResults<CachedResult> getCachedResult(Activity act) {

        Realm realm = getRealmInstance(act);
        final RealmResults<CachedResult> result = realm.where(CachedResult.class).findAll();

        return result;
    }

    //cached default request
    public static void cachedResult(Activity act, String cachedResult, String cachedApi) {

        Realm realm = getRealmInstance(act);

        try {
            final RealmResults<CachedResult> result = realm.where(CachedResult.class).findAll();
            realm.beginTransaction();
            result.clear();
            realm.commitTransaction();
        } catch (Exception e) {
            Log.e("cachedResult1", e.getMessage());
        }

        try {
            realm.beginTransaction();
            CachedResult realmObject = realm.createObject(CachedResult.class);
            realmObject.setCachedResult(cachedResult);
            realmObject.setCachedAPI(cachedApi);
            realm.commitTransaction();
            realm.close();
        } catch (Exception e) {
            Log.e("cachedResul2", e.getMessage());
        }


    }


    public static void clearCachedResult(Activity act) {

        Realm realm = getRealmInstance(act);

        try {
            final RealmResults<CachedResult> result = realm.where(CachedResult.class).findAll();
            realm.beginTransaction();
            result.clear();
            realm.commitTransaction();
            realm.close();
        } catch (Exception e) {
            Log.e("clearCached", e.getMessage());
        }

    }

}
