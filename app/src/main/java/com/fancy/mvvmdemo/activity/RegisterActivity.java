package com.fancy.mvvmdemo.activity;

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
    protected void initData() {
        viewModel.setTitleText("注册");
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_register;
    }
}
