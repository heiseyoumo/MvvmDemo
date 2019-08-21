package com.fancy.mvvmdemo.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

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

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public LiveData<User> getUser(String userName, String gender) {
        Observable<HttpResult<UserBean>> observable = Api.getDefault().login(Util.getRequest(new HashMap<String, String>()));
        /*HttpUtil.getInstance().toSubscribe(observable, new ProgressSubscriber<User>() {
            @Override
            protected void _onNext(User user) {
            }

            @Override
            protected void _onError(String code, String message) {

            }
        });*/
        MutableLiveData<User> user = new MutableLiveData<>();
        user.setValue(new User(userName, gender));
        return user;
    }
}
