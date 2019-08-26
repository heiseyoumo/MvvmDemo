package com.fancy.mvvmdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
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
        this(context, null);
    }

    public TitleBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TitleBarBindingImpl barBinding = DataBindingUtil.inflate(inflater, R.layout.title_bar, this, true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        //获取title
        String title = array.getString(R.styleable.TitleBar_titleBar_title);
        barBinding.titleTv.setText(title);

        String rightTitle = array.getString(R.styleable.TitleBar_titleBar_right_title);
        barBinding.tvRightTitle.setText(rightTitle);
    }
}
