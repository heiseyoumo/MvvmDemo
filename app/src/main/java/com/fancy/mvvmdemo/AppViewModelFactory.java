package com.fancy.mvvmdemo;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.viewmodel.LoginViewModel;

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

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication, appRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
