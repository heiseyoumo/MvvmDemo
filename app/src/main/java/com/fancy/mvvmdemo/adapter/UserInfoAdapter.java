package com.fancy.mvvmdemo.adapter;

import android.content.Context;
import android.databinding.ObservableArrayList;

import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.databinding.RecycleItemInfoBinding;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class UserInfoAdapter extends BaseBindingAdapter<UserBean, RecycleItemInfoBinding> {
    public UserInfoAdapter(Context context, ObservableArrayList<UserBean> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.recycle_item_info;
    }

    @Override
    protected void onBindItem(RecycleItemInfoBinding binding, UserBean userBean) {
        binding.setUserBean(userBean);
    }
}
