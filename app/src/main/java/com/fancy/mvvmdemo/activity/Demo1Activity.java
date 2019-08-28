package com.fancy.mvvmdemo.activity;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;

/**
 * @author pengkuanwang
 * @date 2019-08-28
 */
public class Demo1Activity extends BaseActivity {
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected int initContentView() {
        return R.layout.demo1;
    }

    @Override
    protected void initData() {

    }
}
