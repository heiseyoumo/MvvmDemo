package com.fancy.mvvmdemo.listener;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public interface HttpDataSource {
    //模拟登录
    Observable<HttpResult<UserBean>> login(String name,String pwd);
}
