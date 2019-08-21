package com.fancy.mvvmdemo.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.fancy.mvvmdemo.bean.User;
import com.fancy.mvvmdemo.util.UserRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class UserViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private LiveData<User> user;

    public LiveData<User> getUser(String username, String gender) {
        if (null == user) {
            user = userRepository.getUser(username, gender);
        }
        return user;
    }
}
