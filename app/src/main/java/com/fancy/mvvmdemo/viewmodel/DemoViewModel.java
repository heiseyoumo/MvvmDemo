package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;

import com.fancy.mvvmdemo.model.AppRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class DemoViewModel extends ToolbarViewModel<AppRepository> {
    public ObservableField<String> content = new ObservableField<>("我是你大爷");

    public DemoViewModel(Application application, AppRepository model) {
        super(application, model);
    }
}
