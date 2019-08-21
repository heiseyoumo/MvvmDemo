package com.fancy.mvvmdemo;

import com.fancy.mvvmdemo.util.ToastUtil;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class DemoActivity extends AbsBaseActivity {
    @Override
    protected int initContentView() {
        return R.layout.demo;
    }

    @Override
    protected void initData() {
        ToastUtil.showCustomToast("我是你大爷啊");
    }
}
