package com.fancy.mvvmdemo.fragment;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancy.mvvmdemo.AppViewModelFactory;
import com.fancy.mvvmdemo.BaseViewModel;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pengkuanwang
 * @date 2019-08-28
 */
public abstract class BaseDialogFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends RxDialogFragment {
    protected V binding;
    protected VM viewModel;

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

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((dm.widthPixels - 80), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        int viewModelId = initVariableId();
        viewModel = initViewModel();
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
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        /**
         * 是否指定了泛型
         */
        if (type instanceof ParameterizedType) {
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            modelClass = BaseViewModel.class;
        }
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        viewModel = (VM) ViewModelProviders.of(this, factory).get(modelClass);
        return viewModel;
    }
}
