package com.fancy.mvvmdemo;

import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;

import com.fancy.mvvmdemo.adapter.UserInfoAdapter;
import com.fancy.mvvmdemo.databinding.ActivityRecyclerBinding;
import com.fancy.mvvmdemo.http.UserBean;
import com.fancy.mvvmdemo.model.RecyclerViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class RecyclerViewActivity extends BaseActivity<ActivityRecyclerBinding, RecyclerViewModel> {
    ObservableArrayList<UserBean> userBeans = new ObservableArrayList<>();

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            userBeans.add(new UserBean("pkw" + i, i));
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
