package com.fancy.mvvmdemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.event.SingleLiveEvent;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel {
    protected M model;
    UiChangeLiveData uiChangeLiveData;
    private WeakReference<LifecycleProvider> lifecycle;

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        this.model = model;
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
        System.out.println("结束啦");
        super.onCleared();
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {
        System.out.println("onCreate");
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

    @Override
    public void registerRxBus() {

    }

    @Override
    public void removeRxBus() {

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
}
