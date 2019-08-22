package com.fancy.mvvmdemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.event.SingleLiveEvent;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel {
    protected M model;
    UiChangeLiveData uiChangeLiveData;

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        this.model = model;
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
