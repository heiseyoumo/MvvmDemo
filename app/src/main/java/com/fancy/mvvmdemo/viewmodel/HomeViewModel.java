package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.AppRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-28
 */
public class HomeViewModel extends BaseViewModel<AppRepository> {
    public HomeViewModel(Application application, AppRepository model) {
        super(application, model);
    }
}
