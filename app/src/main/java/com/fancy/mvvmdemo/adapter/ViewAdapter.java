package com.fancy.mvvmdemo.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

import com.fancy.mvvmdemo.view.BindingCommand;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class ViewAdapter {
    @BindingAdapter(value = {"onClickCommand", "isThrottleFirst"}, requireAll = false)
    public static void onClickCommand(View view, final BindingCommand clickCommand, final boolean isThrottleFirst) {
        if (clickCommand != null) {
            clickCommand.execute();
        }
    }
}
