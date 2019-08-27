package com.fancy.mvvmdemo;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.model.AppRepository;

import java.lang.reflect.Constructor;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final AppRepository appRepository;

    private AppViewModelFactory(Application application, AppRepository appRepository) {
        this.mApplication = application;
        this.appRepository = appRepository;
    }

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application, Injection.provideDemoRepository());
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            Constructor<T> constructor = modelClass.getConstructor(Application.class, AppRepository.class);
            return constructor.newInstance(mApplication, appRepository);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }
}
