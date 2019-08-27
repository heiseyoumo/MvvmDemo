package com.fancy.mvvmdemo.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.BottomBarBinding;

/**
 * @author pengkuanwang
 * @date 2019-08-27
 */
public class BottomBar extends RelativeLayout implements RadioGroup.OnCheckedChangeListener {
    BottomBarBinding bottomBarBinding;

    public BottomBar(Context context) {
        super(context);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bottomBarBinding = DataBindingUtil.inflate(inflater, R.layout.bottom_bar, this, true);
        bottomBarBinding.mHomeRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = group.indexOfChild(group.findViewById(checkedId));
        if (onClickBottomBarListener != null) {
            onClickBottomBarListener.onClickBottomBar(index);
        }
    }

    /**
     * 默认选中首页
     *
     * @param tabIndex
     */
    public void setSelected(int tabIndex) {
        ((RadioButton) bottomBarBinding.mHomeRadioGroup.getChildAt(tabIndex)).setChecked(true);
    }

    OnClickBottomBarListener onClickBottomBarListener;

    public void setOnClickBottomBarListener(OnClickBottomBarListener onClickBottomBarListener) {
        this.onClickBottomBarListener = onClickBottomBarListener;
    }

    /**
     * 设置底部栏的点击事件
     */
    public interface OnClickBottomBarListener {
        /**
         * 点击tab
         *
         * @param tabIndex
         */
        void onClickBottomBar(int tabIndex);
    }
}
