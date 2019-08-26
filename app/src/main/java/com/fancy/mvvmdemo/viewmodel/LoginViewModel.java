package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.activity.RegisterActivity;
import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.listener.CallBack;
import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.util.ToastUtil;
import com.fancy.mvvmdemo.view.BindingAction;
import com.fancy.mvvmdemo.view.BindingCommand;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginViewModel extends BaseViewModel<AppRepository> {
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userPwd = new ObservableField<>();

    public LoginViewModel(@NonNull Application application, AppRepository appRepository) {
        super(application, appRepository);
        /**
         * 初始化输入框内容
         */
        userName.set("彭宽旺");
        userPwd.set("000000");
    }

    /**
     * 注册按钮的点击事件
     */
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String name = userName.get();
            String pwd = userPwd.get();
            login(name, pwd);
        }
    });
    /**
     * 注册按钮的点击事件
     */
    public BindingCommand registerOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("phone", userName.get());
            bundle.putString("code", userPwd.get());
            startActivity(RegisterActivity.class);
        }
    });

    /**
     * 登录
     *
     * @param userName
     * @param pwd
     */
    public void login(final String userName, String pwd) {
        Observable<HttpResult<UserBean>> login = model.login(userName, pwd);
        getHttpRequest(login, new CallBack<UserBean>() {
            @Override
            public void onSuccess(UserBean data) {
            }

            @Override
            public void onFail(String code, String errorMsg) {
                ToastUtil.showCustomToast(errorMsg);
            }
        });
    }
}
