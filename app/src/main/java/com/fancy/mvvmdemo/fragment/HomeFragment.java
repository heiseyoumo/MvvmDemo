package com.fancy.mvvmdemo.fragment;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.FragmentHomeBinding;
import com.fancy.mvvmdemo.viewmodel.HomeViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    @Override
    protected void initData() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
