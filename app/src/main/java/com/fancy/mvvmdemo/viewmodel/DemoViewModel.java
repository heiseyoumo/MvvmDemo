package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;

import com.fancy.mvvmdemo.event.SingleLiveEvent;
import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.view.BindingAction;
import com.fancy.mvvmdemo.view.BindingCommand;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class DemoViewModel extends ToolbarViewModel<AppRepository> {
    public SingleLiveEvent<Void> showDialog = new SingleLiveEvent<>();
    public ObservableField<String> content = new ObservableField<>("我是你大爷");

    public BindingCommand showConfirmDialog = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showDialog.call();
        }
    });

    public DemoViewModel(Application application, AppRepository model) {
        super(application, model);
    }

    public void initToolBar() {
        setTitleText("Demo");
    }
}
