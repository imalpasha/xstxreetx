package com.open.project.ui.Model.JSON;

import io.realm.RealmObject;

public class UserInfoJSON extends RealmObject {

    private String userInfo;

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
