package com.fancy.mvvmdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class HuiFuApplication extends Application {
    private static HuiFuApplication instance;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityCallBack();
        BlockCanary.install(this, new AppContext()).start();
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        refWatcher = setupLeakCanary();
        CrashHandler.getInstance().init(this);
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        HuiFuApplication leakApplication = (HuiFuApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
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

    public class AppContext extends BlockCanaryContext {
        private static final String TAG = "AppContext";

        @Override
        public String provideQualifier() {
            String qualifier = "";
            try {
                PackageInfo info = HuiFuApplication.getInstance().getPackageManager()
                        .getPackageInfo(HuiFuApplication.getInstance().getPackageName(), 0);
                qualifier += info.versionCode + "_" + info.versionName + "_YYB";
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "provideQualifier exception", e);
            }
            return qualifier;
        }

        @Override
        public int provideBlockThreshold() {
            return 500;
        }

        @Override
        public boolean displayNotification() {
            return BuildConfig.DEBUG;
        }

        @Override
        public boolean stopWhenDebugging() {
            return false;
        }
    }

    public static HuiFuApplication getInstance() {
        return instance;
    }
}
