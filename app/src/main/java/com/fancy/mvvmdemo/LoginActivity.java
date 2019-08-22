package com.fancy.mvvmdemo;

import android.os.Handler;

import com.fancy.mvvmdemo.bean.User;
import com.fancy.mvvmdemo.databinding.ActivityLoginBinding;
import com.fancy.mvvmdemo.viewmodel.LoginViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    Handler handler = new Handler();

    @Override
    protected void initData() {
        final User user = new User("尤宗华", "女");
        binding.setUser(user);
        binding.setUrl("http://115.159.198.162:3000/posts/57355a92d9ca741017a28375/1467250338739.jpg");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setUserName("pengkuanwang");
            }
        }, 3000);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }
}
