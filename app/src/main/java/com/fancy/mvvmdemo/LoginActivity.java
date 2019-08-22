package com.fancy.mvvmdemo;

import android.arch.lifecycle.ViewModelProviders;
import android.view.View;

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
        /**
         * 使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，
         * 则默认会调用LoginViewModel(@NonNull Application application)构造方法
         */
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    protected void initData() {
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = viewModel.userName.get();
                String pwd = viewModel.userPwd.get();
                viewModel.login(name, pwd);
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.register("15316161570", "1234");
            }
        });

        binding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.show();
            }
        });
    }
}
