package com.fancy.mvvmdemo.activity;

import android.arch.lifecycle.ViewModelProviders;

import com.fancy.mvvmdemo.AppViewModelFactory;
import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.ActivityLoginBinding;
import com.fancy.mvvmdemo.viewmodel.LoginViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    protected void initData() {
    }
}
