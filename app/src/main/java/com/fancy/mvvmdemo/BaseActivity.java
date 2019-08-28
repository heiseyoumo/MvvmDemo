package com.fancy.mvvmdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fancy.mvvmdemo.util.LoadDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {
    protected V binding;
    protected VM viewModel;
    private int variableId;
    private LoadDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.remove("android:support:fragments");
        }
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
        //跳入新页面
        viewModel.getUiChangeLiveData().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                startActivity(clz, bundle);
            }
        });
    }

    public void initViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, initContentView());
        variableId = initVariableId();
        viewModel = initViewModel();
        binding.setVariable(variableId, viewModel);
        /**
         * 通过此方法可以讲BaseViewModel的生命周期跟Activity
         * 的生命周期相互绑定。避免因为生命周期而引起的闪退
         */
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }


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
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        viewModel = (VM) ViewModelProviders.of(this, factory).get(modelClass);
        return viewModel;
    }

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();


    /**
     * 初始化布局文件
     *
     * @return
     */
    protected abstract int initContentView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    private void showDialog() {
        loadDialog = new LoadDialog(this, R.style.LoadingDialogTheme);
        loadDialog.show();
    }

    public void dismissDialog() {
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding.unbind();
        }
    }
}
