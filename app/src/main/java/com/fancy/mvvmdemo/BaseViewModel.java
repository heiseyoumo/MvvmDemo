package com.fancy.mvvmdemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel {
    protected M model;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
