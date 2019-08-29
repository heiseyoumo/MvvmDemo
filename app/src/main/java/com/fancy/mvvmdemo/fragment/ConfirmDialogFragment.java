package com.fancy.mvvmdemo.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.DialogCodeVerifyBinding;
import com.fancy.mvvmdemo.viewmodel.DialogFragmentViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-28
 */
public class ConfirmDialogFragment extends BaseDialogFragment<DialogCodeVerifyBinding, DialogFragmentViewModel> {
    public static ConfirmDialogFragment newInstance(String title, String message) {
        ConfirmDialogFragment fragment = new ConfirmDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        viewModel.closeDialogFragment.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                dismissAllowingStateLoss();
            }
        });
    }

    @Override
    public int initContentView() {
        return R.layout.dialog_code_verify;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
