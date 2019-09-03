package com.fancy.mvvmdemo.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.databinding.FragmentPromptDialogBinding;

import java.io.Serializable;

/**
 * @author pengkuanwang
 * @date 2019-09-03
 */
public class PromptDialogFragment extends DialogFragment {
    private final static String TAG = "PromptDialogFragment";
    /**
     * Dialog提示框的默认宽度
     */
    private final int DEFAULT_WIDTH = 240;
    FragmentPromptDialogBinding binding;
    private static final String BUNDLE_KEY_INFO = "BUNDLE_KEY_INFO";
    private static final String BUNDLE_KEY_CANCEL = "BUNDLE_KEY_CANCEL";
    private static final String BUNDLE_KEY_CONFIRM = "BUNDLE_KEY_CONFIRM";
    private static final String BUNDLE_KEY_OK = "BUNDLE_KEY_OK";
    private static final String BUNDLE_KEY_CALLBACK = "BUNDLE_KEY_CALLBACK";

    public final ObservableField<String> infoValue = new ObservableField<>();
    public final ObservableField<String> cancelValue = new ObservableField<>();
    public final ObservableField<String> confirmValue = new ObservableField<>();
    public final ObservableField<String> okValue = new ObservableField<>();
    private CallBack callBack;

    public static PromptDialogFragment newInstance() {
        PromptDialogFragment fragment = new PromptDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AndroidSupportInjection.inject(this);
        Bundle bundle = getArguments();
        infoValue.set(bundle.getString(BUNDLE_KEY_INFO));
        cancelValue.set(bundle.getString(BUNDLE_KEY_CANCEL));
        confirmValue.set(bundle.getString(BUNDLE_KEY_CONFIRM));
        okValue.set(bundle.getString(BUNDLE_KEY_OK));
        callBack = (CallBack) bundle.getSerializable(BUNDLE_KEY_CALLBACK);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_prompt_dialog, container, false);
        binding.setViewModel(this);
        //设置点击空白区域Dialog框不消失
        setCancelable(false);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialogSize();
    }

    /**
     * 初始化Dialog提示框的大小
     */
    private void initDialogSize() {
        Window window = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        int i = dm.widthPixels - 80;
        params.width = (dm.widthPixels - 80);
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }

    /**
     * 消息框取消按钮的点击事件（左边按钮）
     *
     * @param view
     */
    public void cancelClick(View view) {
        super.dismiss();
        if (callBack != null) {
            callBack.onCancel();
        }
    }

    /**
     * 消息框确认按钮的点击事件（右边按钮）
     *
     * @param view
     */
    public void confirmClick(View view) {
        super.dismiss();
        if (callBack != null) {
            callBack.onSuccess();
        }
    }

    /**
     * 消息框OK按钮的点击事件（仅有一个按钮）
     *
     * @param view
     */
    public void okClick(View view) {
        super.dismiss();
        if (callBack != null) {
            callBack.onSuccess();
        }
    }

    /**
     * Dialog提示框按钮点击事件回调接口
     */
    public interface CallBack extends Serializable {
        void onSuccess();

        void onCancel();
    }

    public static class Builder implements PromptDialogBuilder {
        private String infoValue;
        private String cancelValue = "取消";
        private String confirmValue = "确认";
        private String okValue;
        private CallBack callBack;
        AppCompatActivity appCompatActivity;

        public <T extends AppCompatActivity> Builder(T appCompatActivity) {
            this.appCompatActivity = appCompatActivity;
        }

        @Override
        public PromptDialogBuilder setInfoValue(String value) {
            this.infoValue = value;
            return this;
        }

        @Override
        public PromptDialogBuilder setCancelValue(String value) {
            this.cancelValue = value;
            return this;
        }

        @Override
        public PromptDialogBuilder setConfirmValue(String value) {
            this.confirmValue = value;
            return this;
        }

        @Override
        public PromptDialogBuilder setOKValue(String value) {
            this.okValue = value;
            return this;
        }

        @Override
        public PromptDialogBuilder setCallBack(CallBack callBack) {
            this.callBack = callBack;
            return this;
        }

        @Override
        public PromptDialogFragment build() {
            PromptDialogFragment fragment = PromptDialogFragment.newInstance();
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);
            bundle.putString(BUNDLE_KEY_INFO, infoValue);
            bundle.putString(BUNDLE_KEY_CANCEL, cancelValue);
            bundle.putString(BUNDLE_KEY_CONFIRM, confirmValue);
            bundle.putString(BUNDLE_KEY_OK, okValue);
            bundle.putSerializable(BUNDLE_KEY_CALLBACK, callBack);
            fragment.show(appCompatActivity.getSupportFragmentManager());
            return fragment;
        }
    }

    /**
     * 抽象建造，以规范产品对象的各个组成成分的建造
     */
    public interface PromptDialogBuilder {
        /**
         * 设置提示框提示信息
         *
         * @param value
         * @return
         */
        PromptDialogBuilder setInfoValue(String value);

        /**
         * 设置取消按钮文言（左侧按钮）
         *
         * @param value
         * @return
         */
        PromptDialogBuilder setCancelValue(String value);

        /**
         * 设置确认按钮文言（右侧按钮）
         *
         * @param value
         * @return
         */
        PromptDialogBuilder setConfirmValue(String value);

        /**
         * 设置按钮文言（该按钮与“取消、确认按钮在显示上互斥”）
         *
         * @param value
         * @return
         */
        PromptDialogBuilder setOKValue(String value);

        /**
         * 设置按钮点击事件的回调
         *
         * @param callBack
         * @return
         */
        PromptDialogBuilder setCallBack(CallBack callBack);

        /**
         * 提供产品实例（返回PromptDialogFragment对象）
         *
         * @return
         */
        PromptDialogFragment build();
    }
}
