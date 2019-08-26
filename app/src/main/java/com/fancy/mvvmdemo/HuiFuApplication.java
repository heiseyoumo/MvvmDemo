package com.fancy.mvvmdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class HuiFuApplication extends Application {
    private static HuiFuApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityCallBack();
    }

    public void registerActivityCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }

    public static HuiFuApplication getInstance() {
        return instance;
    }
}
