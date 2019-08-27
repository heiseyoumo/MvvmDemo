package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.AppRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class MainViewModel extends BaseViewModel<AppRepository> {
    public MainViewModel(Application application, AppRepository model) {
        super(application, model);
    }
}
