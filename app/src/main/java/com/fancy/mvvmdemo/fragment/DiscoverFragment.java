package com.fancy.mvvmdemo.fragment;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.FragmentDiscoverBinding;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class DiscoverFragment extends BaseFragment<FragmentDiscoverBinding, BaseViewModel> {
    @Override
    protected void initData() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_discover;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
