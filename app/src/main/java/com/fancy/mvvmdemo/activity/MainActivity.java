package com.fancy.mvvmdemo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.FragmentTab;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.ActivityMainBinding;
import com.fancy.mvvmdemo.fragment.DataFragment;
import com.fancy.mvvmdemo.fragment.DiscoverFragment;
import com.fancy.mvvmdemo.fragment.HomeFragment;
import com.fancy.mvvmdemo.fragment.MineFragment;
import com.fancy.mvvmdemo.view.BottomBar;
import com.fancy.mvvmdemo.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    FragmentTab fragmentTab;

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
        fragmentTab = new FragmentTab(this, R.id.frameLayout);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        initFragment();
        binding.bottomBar.setSelected(0);
        binding.bottomBar.setOnClickBottomBarListener(new BottomBar.OnClickBottomBarListener() {
            @Override
            public void onClickBottomBar(int tabIndex) {
                fragmentTab.show(tabIndex);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
            }
        }, 2000);
    }

    private void initFragment() {
        fragmentTab.add(HomeFragment.class);
        fragmentTab.add(DataFragment.class);
        fragmentTab.add(DiscoverFragment.class);
        fragmentTab.add(MineFragment.class);
        fragmentTab.show(0);
    }
}