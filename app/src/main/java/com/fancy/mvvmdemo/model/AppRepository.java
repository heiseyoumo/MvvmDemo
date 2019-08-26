package com.fancy.mvvmdemo.model;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.listener.HttpDataSource;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class AppRepository extends BaseModel implements HttpDataSource {
    private final HttpDataSource mHttpDataSource;
    private static AppRepository INSTANCE;

    public AppRepository(HttpDataSource mHttpDataSource) {
        this.mHttpDataSource = mHttpDataSource;
    }

    public static AppRepository getInstance(HttpDataSource httpDataSource) {
        if (INSTANCE == null) {
            synchronized (AppRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppRepository(httpDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void onCleared() {
        super.onCleared();
    }

    @Override
    public Observable<HttpResult<UserBean>> login(String name, String pwd) {
        return mHttpDataSource.login(name, pwd);
    }

    @Override
    public Observable<HttpResult<UserBean>> register(String mobileNo, String code) {
        return mHttpDataSource.register(mobileNo, code);
    }
}
