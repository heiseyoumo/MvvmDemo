package com.fancy.mvvmdemo.viewmodel;

import com.fancy.mvvmdemo.BaseViewModel;

/**
 * @author pengkuanwang
 * @date 2019-09-01
 */
public class RecyclerItemViewModel<VM extends BaseViewModel> extends ItemViewModel<VM> {
    protected Object multiType;

    public Object getItemType() {
        return multiType;
    }

    public void multiItemType(Object multiType) {
        this.multiType = multiType;
    }

    public RecyclerItemViewModel(VM viewModel) {
        super(viewModel);
    }
}
