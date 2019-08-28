package com.fancy.mvvmdemo;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.model.AppRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
            if (modelClass.isAssignableFrom(BaseViewModel.class)) {
                return (T) new BaseViewModel(mApplication, null);
            }
            Constructor<T> constructor = modelClass.getConstructor(Application.class, AppRepository.class);
            return constructor.newInstance(mApplication, appRepository);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
