package com.fancy.mvvmdemo.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

import com.fancy.mvvmdemo.view.BindingCommand;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class ViewAdapter {
    /**
     * 1一秒之内防止重复点击
     */
    public static final int CLICK_INTERVAL = 1;

    @BindingAdapter(value = {"onClickCommand"})
    public static void onClickCommand(View view, final BindingCommand clickCommand) {
        RxView.clicks(view)
                .throttleFirst(CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (clickCommand != null) {
                            clickCommand.execute();
                        }
                    }
                });
    }

}
