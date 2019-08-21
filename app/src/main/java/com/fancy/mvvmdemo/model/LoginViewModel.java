package com.fancy.mvvmdemo.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.util.ToastUtil;
import com.fancy.mvvmdemo.view.BindingAction;
import com.fancy.mvvmdemo.view.BindingCommand;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginViewModel extends AndroidViewModel {
    private BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    private void login() {
        ToastUtil.showCustomToast("登录了啊");
    }
}
