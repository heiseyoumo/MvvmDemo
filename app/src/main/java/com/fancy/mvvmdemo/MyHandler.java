package com.fancy.mvvmdemo;

import android.view.View;
import android.widget.Toast;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class MyHandler {
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "点击事件", Toast.LENGTH_LONG).show();
    }
}
