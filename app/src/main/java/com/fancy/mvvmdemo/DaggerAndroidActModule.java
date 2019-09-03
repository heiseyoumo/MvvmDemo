package com.fancy.mvvmdemo;

import android.app.Activity;

import com.fancy.mvvmdemo.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * @author pengkuanwang
 * @date 2019-08-30
 */
@Module(subcomponents = DaggerAndroidActSubComponent.class)
public abstract class DaggerAndroidActModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindDaggerAndroidActivityInjectorFactory(DaggerAndroidActSubComponent.Builder builder);

    @Provides
    @Singleton
    static Student provideStudent() {
        return new Student();
    }
}
