package com.fancy.mvvmdemo;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AbsBaseActivity {
    V binding;
    VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDataBinding();
        initData();
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, initContentView());
    }

    public void initViewDataBinding() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Class modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            viewModel = (VM) createViewModel(this, modelClass);
        }
    }


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding.unbind();
        }
    }
}
