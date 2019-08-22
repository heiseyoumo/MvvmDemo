package com.fancy.mvvmdemo.util;

import com.fancy.mvvmdemo.http.ProgressObserver;
import com.fancy.mvvmdemo.http.RxHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class HttpUtil {
    /**
     * 构造方法私有
     */
    private HttpUtil() {
    }

    /**
     * 在访问HttpUtil时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void toSubscribe(Observable ob, final ProgressObserver subscriber) {
        ObservableTransformer transformer = RxHelper.handleResult();
        ob.compose(transformer)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        subscriber.showProgressDialog();
                    }
                }).subscribe(subscriber);
    }
}
