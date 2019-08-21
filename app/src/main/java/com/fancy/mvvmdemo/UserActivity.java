package com.fancy.mvvmdemo;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.fancy.mvvmdemo.bean.User;
import com.fancy.mvvmdemo.databinding.ActivityUserBinding;
import com.fancy.mvvmdemo.model.UserViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class UserActivity extends BaseActivity<ActivityUserBinding, UserViewModel> {
    @Override
    protected int initContentView() {
        return R.layout.activity_user;
    }

    @Override
    protected void initData() {
        viewModel.getUser("name", "gender").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                binding.setUser(user);
            }
        });
    }
}
