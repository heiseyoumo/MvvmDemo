package com.fancy.mvvmdemo.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import com.fancy.mvvmdemo.AppViewModelFactory;
import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.ActivityMainBinding;
import com.fancy.mvvmdemo.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        /**
         * 使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，
         * 则默认会调用LoginViewModel(@NonNull Application application)构造方法
         */
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void initData() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
