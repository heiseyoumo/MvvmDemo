package com.fancy.mvvmdemo;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.fancy.mvvmdemo.bean.User;
import com.fancy.mvvmdemo.databinding.ActivityLoginBinding;
import com.fancy.mvvmdemo.model.UserViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {
    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        viewModel.getUser("我是你大爷啊", "11111男").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                binding.setUser(user);
            }
        });
    }
}
