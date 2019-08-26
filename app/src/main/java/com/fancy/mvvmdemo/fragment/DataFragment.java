package com.fancy.mvvmdemo.fragment;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.FragmentDataBinding;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class DataFragment extends BaseFragment<FragmentDataBinding, BaseViewModel> {
    @Override
    public int initContentView() {
        return R.layout.fragment_data;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
