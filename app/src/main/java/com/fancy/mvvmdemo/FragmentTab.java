package com.fancy.mvvmdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fancy.mvvmdemo.util.GeneratedClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pengkuanwang
 * @date 2019-08-30
 */
public class FragmentTab {
    private Stack<Fragment> stack = new Stack<>();
    private List<Fragment> list = new ArrayList<>();
    FragmentActivity mActivity;
    private int containerId;

    public FragmentTab(FragmentActivity activity, int frameLayout) {
        mActivity = activity;
        containerId = frameLayout;
    }

    /**
     * 展示选中的fragment
     *
     * @param tabIndex
     */
    public void show(int tabIndex) {
        Fragment fragment = list.get(tabIndex);
        FragmentManager manager = mActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!stack.isEmpty()) {
            Fragment currentFragment = stack.pop();
            transaction.hide(currentFragment);
        }
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(containerId, fragment);
        }
        stack.push(fragment);
        if (mActivity != null && !mActivity.isFinishing()) {
            transaction.commitAllowingStateLoss();
            manager.executePendingTransactions();
        }
    }

    public void add(Class<? extends Fragment> clazz) {
        if (clazz != null) {
            list.add(GeneratedClassUtils.getInstance(clazz));
        }
    }
}
