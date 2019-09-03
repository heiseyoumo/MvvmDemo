package com.fancy.mvvmdemo;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * @author pengkuanwang
 * @date 2019-08-30
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class,})
public interface AppComponent {
    void inject(HuiFuApplication application);
}