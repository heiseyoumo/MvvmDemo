package com.fancy.mvvmdemo.util;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StyleRes;

import com.fancy.mvvmdemo.R;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class LoadDialog extends AlertDialog {
    public LoadDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.load_view);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }
}
