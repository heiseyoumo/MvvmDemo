package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.util.ToastUtil;

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
    }

    /**
     * 登录按钮点击事件
     */
    public void loginOnClickCommand(View view) {
        String name = userName.get();
        String pwd = userPwd.get();
        login(name, pwd);
    }

    /**
     * 登录
     *
     * @param userName
     * @param pwd
     */
    public void login(String userName, String pwd) {
        ToastUtil.showCustomToast("姓名:" + userName + ",密码:" + pwd);
        Observable<HttpResult<UserBean>> login = model.login(userName, pwd);
    }

    /**
     * 注册
     *
     * @param phone
     * @param code
     */
    public void register(String phone, String code) {
        ToastUtil.showCustomToast("姓名:" + userName + ",密码:" + phone);
        Observable<HttpResult<UserBean>> login = model.register(phone, code);
    }
}
