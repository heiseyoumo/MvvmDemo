package com.fancy.mvvmdemo.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.fancy.mvvmdemo.AppViewModelFactory;
import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.ActivityMainBinding;
import com.fancy.mvvmdemo.fragment.DataFragment;
import com.fancy.mvvmdemo.fragment.DiscoverFragment;
import com.fancy.mvvmdemo.fragment.HomeFragment;
import com.fancy.mvvmdemo.fragment.MineFragment;
import com.fancy.mvvmdemo.util.GeneratedClassUtils;
import com.fancy.mvvmdemo.view.BottomBar;
import com.fancy.mvvmdemo.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private List<Fragment> mFragments;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        /**
         * 使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，
         * 则默认会调用LoginViewModel(@NonNull Application application)构造方法
         */
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void initData() {
        initFragment();
        binding.bottomBar.setSelected(0);
        binding.bottomBar.setOnClickBottomBarListener(new BottomBar.OnClickBottomBarListener() {
            @Override
            public void onClickBottomBar(int tabIndex) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, mFragments.get(tabIndex));
                transaction.commitAllowingStateLoss();
            }
        });
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(GeneratedClassUtils.getInstance(HomeFragment.class));
        mFragments.add(GeneratedClassUtils.getInstance(DataFragment.class));
        mFragments.add(GeneratedClassUtils.getInstance(DiscoverFragment.class));
        mFragments.add(GeneratedClassUtils.getInstance(MineFragment.class));
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, mFragments.get(0));
        transaction.commitAllowingStateLoss();
    }
}
