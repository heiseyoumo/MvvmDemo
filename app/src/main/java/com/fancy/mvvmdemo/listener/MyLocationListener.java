package com.fancy.mvvmdemo.listener;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class MyLocationListener implements LifecycleObserver {
    private boolean enabled = false;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        if (enabled) {
        }
    }

    public void enable() {
        enabled = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
    }
}
