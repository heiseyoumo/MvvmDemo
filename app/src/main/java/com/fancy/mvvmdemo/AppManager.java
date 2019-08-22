package com.fancy.mvvmdemo;

import android.app.Activity;

import java.util.Stack;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class AppManager {
    private static AppManager instance;
    private static Stack<Activity> activityStack;

    private AppManager() {
    }

    /**
     * 单例模式
     *
     * @return AppManager
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }
}
