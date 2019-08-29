package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.event.SingleLiveEvent;
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
 * @date 2019-08-29
 */
public class DialogFragmentViewModel extends BaseViewModel<AppRepository> {
    private static final int COUNTDOWN_TIME = 60;
    private Disposable countDisposable;
    public ObservableField<String> sendCodeContent = new ObservableField<>("获取验证码");
    public ObservableField<Boolean> sendCodeEnabled = new ObservableField<>(true);
    public SingleLiveEvent<Void> closeDialogFragment = new SingleLiveEvent<>();
    public ObservableField<String> code = new ObservableField<>();

    public DialogFragmentViewModel(@NonNull Application application, AppRepository model) {
        super(application, model);
    }

    public BindingCommand backListener = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            closeDialogFragment.call();
        }
    });
    public BindingCommand sendCodeListener = new BindingCommand(new BindingAction() {
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

            showDialog();
        }
    });
    public BindingCommand confirmListener = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String s = code.get();
            if (TextUtils.isEmpty(s)) {
                ToastUtil.showCustomToast("请输入验证码");
                return;
            }
            if (s.length() != 6) {
                ToastUtil.showCustomToast("请输入6位验证码");
                return;
            }
            ToastUtil.showCustomToast(s);
            closeDialogFragment.call();
        }
    });

    @Override
    protected void onCleared() {
        super.onCleared();
        if (countDisposable != null) {
            countDisposable.dispose();
        }
    }
}
