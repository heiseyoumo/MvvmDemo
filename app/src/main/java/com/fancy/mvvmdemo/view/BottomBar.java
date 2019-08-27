package com.fancy.mvvmdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class BottomBar extends RelativeLayout implements RadioGroup.OnCheckedChangeListener{
    public BottomBar(Context context) {
        super(context);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
