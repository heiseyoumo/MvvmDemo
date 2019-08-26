package com.fancy.mvvmdemo.activity;

import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.adapter.UserInfoAdapter;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.databinding.ActivityRecyclerBinding;
import com.fancy.mvvmdemo.viewmodel.RecyclerViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class RecyclerViewActivity extends BaseActivity<ActivityRecyclerBinding, RecyclerViewModel> {
    ObservableArrayList<UserBean> userBeans = new ObservableArrayList<>();

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            userBeans.add(new UserBean("pkw" + i, String.valueOf(i)));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(new UserInfoAdapter(this, userBeans));
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_recycler;
    }
}
