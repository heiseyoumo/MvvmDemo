package com.fancy.mvvmdemo;

import com.fancy.mvvmdemo.bean.User;
import com.fancy.mvvmdemo.databinding.ActivityLoginBinding;
import com.fancy.mvvmdemo.model.LoginViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    protected void initData() {
        binding.setUser(new User("尤宗华", "女"));
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }
}
