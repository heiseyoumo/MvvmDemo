package com.fancy.mvvmdemo.activity;

import android.arch.lifecycle.ViewModelProviders;

import com.fancy.mvvmdemo.AppViewModelFactory;
import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.ActivityRegisterBinding;
import com.fancy.mvvmdemo.viewmodel.RegisterViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public RegisterViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(RegisterViewModel.class);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_register;
    }
}
