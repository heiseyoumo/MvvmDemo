package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.RecyclerModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class RecyclerViewModel extends BaseViewModel<RecyclerModel> {
    public RecyclerViewModel(@NonNull Application application) {
        super(application);
    }
}
