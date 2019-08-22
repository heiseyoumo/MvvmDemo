package com.fancy.mvvmdemo.listener;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;
import com.fancy.mvvmdemo.http.ApiService;
import com.fancy.mvvmdemo.util.Util;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private ApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    private HttpDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    public static HttpDataSourceImpl getInstance(ApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<HttpResult<UserBean>> login(String name, String pwd) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", "name");
        hashMap.put("pwd", "pwd");
        Observable<HttpResult<UserBean>> login = apiService.login(Util.getRequest(hashMap));
        return login;
    }
}
