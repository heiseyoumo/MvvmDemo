package com.fancy.mvvmdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.fancy.mvvmdemo.util.LoadDialog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AbsBaseActivity {
    V binding;
    VM viewModel;
    private int variableId;
    private LoadDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDataBinding();
        registerUiChangeLiveDataCallBack();
        initData();
    }

    /**
     * BaseView和BaseActivity通过LiveData来处理常用UI逻辑
     * 即可在ViewModel中使用父类的showDialog()、startActivity()等方法。
     * 在这个BaseViewModel中就可以尽情的写你的逻辑了！
     */
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
        /**
         * 关闭界面
         */
        viewModel.getUiChangeLiveData().getFinishEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, initContentView());
    }

    public void initViewDataBinding() {
        variableId = initVariableId();
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
        binding.setVariable(variableId, viewModel);
    }

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

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

    private void showDialog() {
        if (loadDialog != null) {
            loadDialog.show();
        } else {
            loadDialog = new LoadDialog(this, R.style.LoadingDialogTheme);
            loadDialog.show();
        }
    }

    public void dismissDialog() {
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding.unbind();
        }
    }
}
