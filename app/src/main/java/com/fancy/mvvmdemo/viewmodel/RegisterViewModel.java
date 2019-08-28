package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.view.View;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.listener.CallBack;
import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.util.ToastUtil;
import com.fancy.mvvmdemo.view.BindingAction;
import com.fancy.mvvmdemo.view.BindingCommand;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class RegisterViewModel extends ToolbarViewModel<AppRepository> {
    private static final int COUNTDOWN_TIME = 60;
    private Disposable countDisposable;

    public ObservableField<String> sendCodeContent = new ObservableField<>("发送验证码");
    public ObservableField<Boolean> sendCodeEnabled = new ObservableField<>(true);

    public RegisterViewModel(Application application, AppRepository model) {
        super(application, model);
    }

    public View.OnClickListener onClickRegister = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            register("15316161570", "1234");
        }
    };

    public BindingCommand onSendCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            countDisposable = Observable.intervalRange(1, COUNTDOWN_TIME, 0, 1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            sendCodeContent.set((COUNTDOWN_TIME - aLong) + "s倒计时");
                            sendCodeEnabled.set(false);
                        }
                    }).doOnComplete(new Action() {
                        @Override
                        public void run() {
                            sendCodeEnabled.set(true);
                            sendCodeContent.set("重新发送");
                        }
                    }).subscribe();
        }
    });

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

    @Override
    protected void onCleared() {
        super.onCleared();
        if (countDisposable != null) {
            countDisposable.dispose();
        }
    }
}
