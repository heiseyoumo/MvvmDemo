package com.fancy.mvvmdemo.viewmodel;

import com.fancy.mvvmdemo.BaseViewModel;

/**
 * @author pengkuanwang
 * @date 2019-09-01
 */
public class ItemViewModel<VM extends BaseViewModel> {
    protected VM viewModel;

    public ItemViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }
}
