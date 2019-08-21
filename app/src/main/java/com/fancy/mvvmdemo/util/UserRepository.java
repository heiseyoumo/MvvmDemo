package com.fancy.mvvmdemo.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.User;
import com.fancy.mvvmdemo.http.Api;
import com.fancy.mvvmdemo.http.UserBean;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class UserRepository {
    private static final UserRepository instance = new UserRepository();
    private Handler handler = new Handler(Looper.getMainLooper());


    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public LiveData<User> getUser(final String userName, final String gender) {
        final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
        Observable<HttpResult<UserBean>> observable = Api.getDefault().login(Util.getRequest(new HashMap<String, String>()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mutableLiveData.setValue(new User("彭宽旺", "男"));
                    }
                });
            }
        }).start();
        /*HttpUtil.getInstance().toSubscribe(observable, new ProgressSubscriber<User>() {
            @Override
            protected void _onNext(User user) {
                mutableLiveData.setValue(user);
            }

            @Override
            protected void _onError(String code, String message) {

            }
        });*/
        return mutableLiveData;
    }
}
