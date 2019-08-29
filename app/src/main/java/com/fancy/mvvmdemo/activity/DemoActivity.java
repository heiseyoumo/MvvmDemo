package com.fancy.mvvmdemo.activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.DemoBinding;
import com.fancy.mvvmdemo.fragment.ConfirmDialogFragment;
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
        viewModel.showDialog.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance("标题", "信息", new ConfirmDialogFragment.ConfirmDialogListener() {
                    @Override
                    public void sure() {

                    }
                });
                confirmDialogFragment.show(getSupportFragmentManager());
            }
        });
    }
}
