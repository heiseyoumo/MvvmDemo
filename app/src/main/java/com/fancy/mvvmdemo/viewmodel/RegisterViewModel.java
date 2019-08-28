package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.view.View;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.listener.CallBack;
import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.util.ToastUtil;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class RegisterViewModel extends ToolbarViewModel<AppRepository> {
    public RegisterViewModel(Application application, AppRepository model) {
        super(application, model);
    }

    public View.OnClickListener onClickRegister = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            register("15316161570", "1234");
        }
    };

    /**
     * 注册
     *
     * @param phone
     * @param code
     */
    public void register(String phone, String code) {
        ToastUtil.showCustomToast("手机号码:" + phone + ",验证码:" + code);
        Observable<HttpResult<UserBean>> register = model.register(phone, code);
        getHttpRequest(register, new CallBack<UserBean>() {
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
