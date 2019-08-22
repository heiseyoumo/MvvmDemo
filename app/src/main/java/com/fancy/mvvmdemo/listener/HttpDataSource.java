package com.fancy.mvvmdemo.listener;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;

import io.reactivex.Observable;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public interface HttpDataSource {
    /**
     * 登录接口
     *
     * @param name
     * @param pwd
     * @return
     */
    Observable<HttpResult<UserBean>> login(String name, String pwd);

    /**
     * 注册接口
     *
     * @param mobileNo
     * @param code
     * @return
     */
    Observable<HttpResult<UserBean>> register(String mobileNo, String code);
}
