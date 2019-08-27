package com.fancy.mvvmdemo.activity;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.DemoBinding;
import com.fancy.mvvmdemo.viewmodel.DemoViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class DemoActivity extends BaseActivity<DemoBinding, DemoViewModel> {
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected int initContentView() {
        return R.layout.demo;
    }

    @Override
    protected void initData() {
        viewModel.initToolBar();
    }
}
