package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.AppModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class PersonViewModel extends BaseViewModel<AppModel> {
    public PersonViewModel(@NonNull Application application, AppModel appModel) {
        super(application,appModel);
    }
}
