package com.fancy.mvvmdemo;

import android.app.Application;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
