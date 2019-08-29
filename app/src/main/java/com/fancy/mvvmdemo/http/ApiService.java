package com.fancy.mvvmdemo.http;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public interface ApiService {
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(ApiUrl.LOGIN)
    Observable<HttpResult<UserBean>> login(@Body RequestBody request);

    @POST(ApiUrl.REGISTER)
    Observable<HttpResult<UserBean>> register(@Body RequestBody request);
}
