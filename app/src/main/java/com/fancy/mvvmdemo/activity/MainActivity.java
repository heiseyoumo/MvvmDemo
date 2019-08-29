package com.fancy.mvvmdemo.activity;

import android.Manifest;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

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
    protected void initData() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(DemoActivity.class);
            }
        }, 2000);
    }
}