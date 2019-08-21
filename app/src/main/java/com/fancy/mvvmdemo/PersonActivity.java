package com.fancy.mvvmdemo;

import android.os.Handler;

import com.fancy.mvvmdemo.databinding.ActivityPersonBinding;
import com.fancy.mvvmdemo.model.PersonViewModel;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class PersonActivity extends BaseActivity<ActivityPersonBinding, PersonViewModel> {
    Handler handler = new Handler();

    @Override
    protected int initContentView() {
        return R.layout.activity_person;
    }

    @Override
    protected void initData() {
        binding.setViewModel(viewModel);
    }
}
