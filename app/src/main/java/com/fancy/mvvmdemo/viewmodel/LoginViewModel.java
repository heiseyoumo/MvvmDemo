package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.model.AppModel;
import com.fancy.mvvmdemo.util.ToastUtil;
import com.fancy.mvvmdemo.view.BindingAction;
import com.fancy.mvvmdemo.view.BindingCommand;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginViewModel extends BaseViewModel<AppModel> {
    private ObservableField<String> userName = new ObservableField<>();
    private ObservableField<String> userPwd = new ObservableField<>();

    private Handler handler = new Handler(Looper.getMainLooper());

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 登录按钮点击事件
     */
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            String name = userName.get();
            String pwd = userPwd.get();
            login(name, pwd);
        }
    });

    public void login(String userName, String pwd) {
        ToastUtil.showCustomToast("姓名:" + userName + ",密码:" + pwd);
        model.login();
    }

    public MutableLiveData<UserBean> Login1(Context context, String userName, String pwd) {
        final MutableLiveData<UserBean> mutableLiveData = new MutableLiveData<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mutableLiveData.setValue(new UserBean("pkw", "23"));
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
