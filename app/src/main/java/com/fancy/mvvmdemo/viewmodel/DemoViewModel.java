package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;

import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.view.BindingAction;
import com.fancy.mvvmdemo.view.BindingCommand;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class DemoViewModel extends ToolbarViewModel<AppRepository> {
    public ObservableField<String> content = new ObservableField<>("我是你大爷");

    public BindingCommand showConfirmDialog = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Integer.parseInt("231e");
        }
    });

    public DemoViewModel(Application application, AppRepository model) {
        super(application, model);
    }

    public void initToolBar() {
        setTitleText("Demo");
    }
}
