package com.fancy.mvvmdemo;

import com.fancy.mvvmdemo.http.ApiService;
import com.fancy.mvvmdemo.listener.HttpDataSource;
import com.fancy.mvvmdemo.listener.HttpDataSourceImpl;
import com.fancy.mvvmdemo.model.AppRepository;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class Injection {
    public static AppRepository provideDemoRepository() {
        //网络API服务
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //两条分支组成一个数据仓库
        return AppRepository.getInstance(httpDataSource);
    }
}
