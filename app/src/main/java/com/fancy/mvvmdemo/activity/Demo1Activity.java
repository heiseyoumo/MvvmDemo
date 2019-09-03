package com.fancy.mvvmdemo.activity;

import android.view.View;

import com.fancy.mvvmdemo.BR;
import com.fancy.mvvmdemo.BaseActivity;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.fragment.PromptDialogFragment;
import com.fancy.mvvmdemo.util.ToastUtil;

/**
 * @author pengkuanwang
 * @date 2019-08-28
 */
public class Demo1Activity extends BaseActivity {
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected int initContentView() {
        return R.layout.demo1;
    }

    @Override
    protected void initData() {
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PromptDialogFragment.Builder(Demo1Activity.this)
                        .setCancelValue("你的芽儿啊").setConfirmValue("confirm").setInfoValue("infoValue")
                        .setCancelValue("cancelValue")
                        .setInfoValue("infoValue").setCallBack(new PromptDialogFragment.CallBack() {
                    @Override
                    public void onSuccess() {
                        ToastUtil.showCustomToast("成功");
                    }

                    @Override
                    public void onCancel() {
                        ToastUtil.showCustomToast("失败");
                    }
                }).build();
            }
        });
    }
}
