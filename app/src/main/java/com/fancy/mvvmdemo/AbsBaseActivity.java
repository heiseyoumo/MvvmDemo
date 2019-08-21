package com.fancy.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public abstract class AbsBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.remove("android:support:fragments");
        }
        initView();
    }

    protected void initView() {
        DataBindingUtil.setContentView(this, initContentView());
    }


    /**
     * 初始化布局文件
     *
     * @return
     */
    protected abstract int initContentView();
}
