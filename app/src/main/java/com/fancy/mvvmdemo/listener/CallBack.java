package com.fancy.mvvmdemo.listener;

/**
 * @author pengkuanwang
 * @date 2019-08-23
 */
public interface CallBack<T> {

    void onSuccess(T data);

    void onFail(String code, String errorMsg);
}
