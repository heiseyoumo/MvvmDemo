package com.fancy.mvvmdemo.model;

import com.fancy.mvvmdemo.BaseModel;
import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.listener.HttpDataSource;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class AppModel extends BaseModel implements HttpDataSource {
    private final HttpDataSource mHttpDataSource;
    private static AppModel INSTANCE;

    public AppModel(HttpDataSource mHttpDataSource) {
        this.mHttpDataSource = mHttpDataSource;
    }

    public static AppModel getInstance(HttpDataSource httpDataSource) {
        if (INSTANCE == null) {
            synchronized (AppModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppModel(httpDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<HttpResult<UserBean>> login() {
        return mHttpDataSource.login();
    }
}
