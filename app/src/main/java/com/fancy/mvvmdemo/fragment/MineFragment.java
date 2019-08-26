package com.fancy.mvvmdemo.fragment;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.FragmentMineBinding;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class MineFragment extends BaseFragment<FragmentMineBinding, BaseViewModel>  {
    @Override
    protected void initData() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
