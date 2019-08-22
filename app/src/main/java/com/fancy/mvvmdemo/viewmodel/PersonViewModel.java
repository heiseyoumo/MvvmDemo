package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.PersonModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class PersonViewModel extends BaseViewModel<PersonModel> {
    public PersonViewModel(@NonNull Application application) {
        super(application);
    }
}
