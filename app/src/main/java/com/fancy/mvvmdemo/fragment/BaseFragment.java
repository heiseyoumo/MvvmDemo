package com.fancy.mvvmdemo.fragment;

import android.arch.lifecycle.Observer;
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

import com.fancy.mvvmdemo.AppViewModelFactory;
import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.util.LoadDialog;
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
    private LoadDialog loadDialog;

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
        registerUiChangeLiveDataCallBack();
        initData();
    }

    private void registerUiChangeLiveDataCallBack() {
        /**
         * 加载框展示
         */
        viewModel.getUiChangeLiveData().getShowDialogEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println(s);
                showDialog();
            }
        });

        /**
         * 加载框消失
         */
        viewModel.getUiChangeLiveData().getDismissDialogEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                dismissDialog();
            }
        });
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        viewModel = initViewModel();
        binding.setVariable(initVariableId(), viewModel);
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

    private void showDialog() {
        loadDialog = new LoadDialog(getActivity(), R.style.LoadingDialogTheme);
        loadDialog.show();
    }

    public void dismissDialog() {
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
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
