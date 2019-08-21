package com.fancy.mvvmdemo.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.fancy.mvvmdemo.http.UserBean;
import com.fancy.mvvmdemo.util.ToastUtil;
import com.fancy.mvvmdemo.view.BindingAction;
import com.fancy.mvvmdemo.view.BindingCommand;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginViewModel extends ViewModel {
    private Handler handler = new Handler(Looper.getMainLooper());
    private BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    private void login() {
        ToastUtil.showCustomToast("登录了啊");
    }

    public MutableLiveData<UserBean> Login(Context context, String userName, String pwd) {
        final MutableLiveData<UserBean> mutableLiveData = new MutableLiveData<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mutableLiveData.setValue(new UserBean("pkw", 23));
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return mutableLiveData;
    }
}
