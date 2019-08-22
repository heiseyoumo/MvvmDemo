package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.AppRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class PersonViewModel extends BaseViewModel<AppRepository> {
    public PersonViewModel(@NonNull Application application, AppRepository appRepository) {
        super(application, appRepository);
    }
}
