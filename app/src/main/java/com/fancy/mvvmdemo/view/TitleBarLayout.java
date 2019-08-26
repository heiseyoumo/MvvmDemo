package com.fancy.mvvmdemo.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.TitleBarBindingImpl;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public class TitleBarLayout extends RelativeLayout {
    public TitleBarLayout(Context context) {
        super(context);
    }

    public TitleBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TitleBarBindingImpl barBinding = DataBindingUtil.inflate(inflater, R.layout.title_bar, this, true);
    }
}
