package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.util.ToastUtil;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginViewModel extends BaseViewModel<AppRepository> {
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userPwd = new ObservableField<>();

    public LoginViewModel(@NonNull Application application, AppRepository appRepository) {
        super(application, appRepository);
    }

    /**
     * 登录按钮点击事件
     */
    public void loginOnClickCommand(View view) {
        String name = userName.get();
        String pwd = userPwd.get();
        login(name, pwd);
    }

    public void login(String userName, String pwd) {
        ToastUtil.showCustomToast("姓名:" + userName + ",密码:" + pwd);
        model.login(userName, pwd);
    }
}
