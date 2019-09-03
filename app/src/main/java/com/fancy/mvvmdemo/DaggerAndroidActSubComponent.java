package com.fancy.mvvmdemo;

import com.fancy.mvvmdemo.activity.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author pengkuanwang
 * @date 2019-08-30
 */
@Subcomponent(modules = AndroidInjectionModule.class)
public interface DaggerAndroidActSubComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}
