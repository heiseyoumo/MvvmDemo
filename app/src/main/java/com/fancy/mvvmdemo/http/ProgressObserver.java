package com.fancy.mvvmdemo.http;

import android.content.Context;

import com.fancy.mvvmdemo.R;
import com.fancy.mvvmdemo.util.LoadDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public abstract class ProgressObserver<T> implements Observer<T> {

    LoadDialog loadDialog;
    private Context context;

    public ProgressObserver(Context context) {
        this(context, true);
    }

    /**
     * @param context
     * @param isShowDialog
     */
    public ProgressObserver(Context context, boolean isShowDialog) {
        this.context = context;
        //初始化一个dialog
        if (isShowDialog) {
            loadDialog = new LoadDialog(context, R.style.LoadingDialogTheme);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            String message = e.getMessage();
            String code = ((ApiException) e).getCode();
            _onError(code, message);
        }
    }

    @Override
    public void onNext(T value) {
        _onNext(value);
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String code, String message);

    /**
     * 取消Dialog
     */
    private void dismissProgressDialog() {
        if (loadDialog != null) {
            loadDialog.dismiss();
        }
    }

    /**
     * 显示Dialog
     */
    public void showProgressDialog() {
        if (loadDialog != null) {
            loadDialog.show();
        }
    }
}
