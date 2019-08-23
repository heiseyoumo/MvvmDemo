package com.fancy.mvvmdemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.event.SingleLiveEvent;
import com.fancy.mvvmdemo.http.ApiException;
import com.fancy.mvvmdemo.http.RxHelper;
import com.fancy.mvvmdemo.listener.CallBack;
import com.fancy.mvvmdemo.util.CommonUtil;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel {
    protected M model;
    UiChangeLiveData uiChangeLiveData;
    private WeakReference<LifecycleProvider> lifecycle;
    /**
     * 管理RxJava，主要针对RxJava异步操作造成的内存泄漏
     */
    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        this.model = model;
        mCompositeDisposable = new CompositeDisposable();
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    public void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = new WeakReference<>(lifecycle);
    }

    public LifecycleProvider getLifecycleProvider() {
        return lifecycle.get();
    }

    public UiChangeLiveData getUiChangeLiveData() {
        if (uiChangeLiveData == null) {
            uiChangeLiveData = new UiChangeLiveData();
        }
        return uiChangeLiveData;
    }

    protected void showDialog() {
        showDialog("请稍后...");
    }

    protected void showDialog(String title) {
        uiChangeLiveData.showDialogEvent.postValue(title);
    }

    protected void dismissDialog() {
        uiChangeLiveData.dismissDialogEvent.call();
    }

    public void finish() {
        uiChangeLiveData.finishEvent.call();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        /**
         * ViewModel销毁时会执行，同时取消所有异步任务
         */
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        System.out.println(owner);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");
    }

    @Override
    public void onStart() {
        System.out.println("onStart");
    }

    @Override
    public void onStop() {
        System.out.println("onStop");
    }

    @Override
    public void onResume() {
        System.out.println("onResume");
    }

    @Override
    public void onPause() {
        System.out.println("onPause");
    }

    public final class UiChangeLiveData extends SingleLiveEvent {
        private SingleLiveEvent<String> showDialogEvent;
        private SingleLiveEvent<Void> dismissDialogEvent;
        private SingleLiveEvent<Void> finishEvent;

        public SingleLiveEvent<String> getShowDialogEvent() {
            return showDialogEvent = createLiveData(showDialogEvent);
        }

        public SingleLiveEvent<Void> getDismissDialogEvent() {
            return dismissDialogEvent = createLiveData(dismissDialogEvent);
        }

        public SingleLiveEvent<Void> getFinishEvent() {
            return finishEvent = createLiveData(finishEvent);
        }

        private SingleLiveEvent createLiveData(SingleLiveEvent liveData) {
            if (liveData == null) {
                liveData = new SingleLiveEvent();
            }
            return liveData;
        }
    }

    /**
     * 发起网络请求
     *
     * @param observable
     * @param callBack
     * @param <T>
     */
    public <T> void getHttpRequest(Observable<HttpResult<T>> observable, final CallBack<T> callBack) {
        ObservableTransformer transformer = RxHelper.handleResult();
        observable.compose(transformer)
                .compose(getLifecycleProvider().bindToLifecycle())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                }).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                addSubscribe(d);
            }

            @Override
            public void onNext(T t) {
                if (callBack != null) {
                    callBack.onSuccess(t);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (!CommonUtil.isNetworkAvailable(MyApplication.getInstance())) {
                    if (callBack != null) {
                        callBack.onFail("-1", "网络无效");
                    }
                } else if (e instanceof ApiException) {
                    if (callBack != null) {
                        callBack.onFail(((ApiException) e).getCode(), e.getMessage());
                    }
                } else {
                    if (callBack != null) {
                        callBack.onFail("-1", "网络异常，请稍后再试");
                    }
                }
                dismissDialog();
            }

            @Override
            public void onComplete() {
                dismissDialog();
            }
        });
    }
}
