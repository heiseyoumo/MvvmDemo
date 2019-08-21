package com.fancy.mvvmdemo.util;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StyleRes;

import com.fancy.mvvmdemo.R;


/**
 * Created by pengkuanwang on 2017/8/2.
 */

public class LoadDialog extends AlertDialog {
    public LoadDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public void dismissLoading() {
        dismiss();
    }

    public void showLoading() {
        show();
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.load_view);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }
}
