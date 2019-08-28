package com.fancy.mvvmdemo.fragment;

import android.os.Bundle;
import android.view.View;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.DialogCodeVerifyBinding;

/**
 * @author pengkuanwang
 * @date 2019-08-28
 */
public class ConfirmDialogFragment extends BaseDialogFragment<DialogCodeVerifyBinding, BaseViewModel> {
    private ConfirmDialogListener mListener;

    public void setmListener(ConfirmDialogListener mListener) {
        this.mListener = mListener;
    }

    public static ConfirmDialogFragment newInstance(String title, String message, ConfirmDialogListener mListener) {
        ConfirmDialogFragment fragment = new ConfirmDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        fragment.setmListener(mListener);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public interface ConfirmDialogListener {

        void sure();
    }
}
