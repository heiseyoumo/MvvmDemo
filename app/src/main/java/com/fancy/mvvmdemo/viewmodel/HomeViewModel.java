package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.activity.LoginActivity;
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
 * @date 2019-08-28
 */
public class HomeViewModel extends BaseViewModel<AppRepository> {
    public HomeViewModel(Application application, AppRepository model) {
        super(application, model);
    }

    public BindingCommand onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(LoginActivity.class);
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
