package com.fancy.mvvmdemo.fragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancy.mvvmdemo.BaseViewModel;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pengkuanwang
 * @date 2019-08-26
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment {
    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, initContentView(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 私有的初始化DataBinding和ViewModel方法
         */
        initViewDataBinding();
        initData();
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        viewModelId = initVariableId();
        viewModel = initViewModel();
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) createViewModel(this, modelClass);
        }
        binding.setVariable(viewModelId, viewModel);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView();

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls) {
        return ViewModelProviders.of(fragment).get(cls);
    }
}
