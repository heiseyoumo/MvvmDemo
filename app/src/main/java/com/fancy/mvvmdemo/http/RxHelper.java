package com.fancy.mvvmdemo.http;

import com.fancy.mvvmdemo.bean.HttpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class RxHelper {

    public static <T> ObservableTransformer<HttpResult<T>, T> handleResult1() {
        ObservableTransformer transformer = new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> tObservable) {
                Observable<T> observable = tObservable.flatMap(new Function<HttpResult<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(HttpResult<T> tHttpResult) throws Exception {
                        return null;
                    }
                });
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                return observable;
            }
        };
        return transformer;
    }

    public static <T> ObservableTransformer<HttpResult<T>, T> handleResult() {
        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> tObservable) {
                return tObservable.flatMap(new Function<HttpResult<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(HttpResult<T> tHttpResult) throws Exception {
                        if (tHttpResult.isSuccess()) {
                            return createData(tHttpResult.getData());
                        } else {
                            return Observable.error(new ApiException(tHttpResult.getRespCode(), tHttpResult.getRespMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                emitter.onNext(data);
                emitter.onComplete();
            }
        });
    }
}
