package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.AppRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class RecyclerViewModel extends BaseViewModel<AppRepository> {
    public RecyclerViewModel(@NonNull Application application, AppRepository appRepository) {
        super(application, appRepository);
    }
}
