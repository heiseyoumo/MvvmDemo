package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.bean.User;
import com.fancy.mvvmdemo.model.AppRepository;
import com.fancy.mvvmdemo.util.UserRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class UserViewModel extends BaseViewModel<AppRepository> {
    private UserRepository userRepository = UserRepository.getInstance();
    private LiveData<User> user;

    public UserViewModel(@NonNull Application application, AppRepository appRepository) {
        super(application, appRepository);
    }

    public LiveData<User> getUser(String username, String gender) {
        if (null == user) {
            user = userRepository.getUser(username, gender);
        }
        return user;
    }
}
