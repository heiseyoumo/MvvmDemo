package com.fancy.mvvmdemo.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class User extends BaseObservable {
    private String userName;
    private String userSex;

    @Bindable
    public String getUserName() {
        return userName;
    }

    @Bindable
    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public User(String userName, String userSex) {
        this.userName = userName;
        this.userSex = userSex;
    }
}
