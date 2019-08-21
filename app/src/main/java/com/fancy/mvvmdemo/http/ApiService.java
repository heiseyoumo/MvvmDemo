package com.fancy.mvvmdemo.http;

import com.fancy.mvvmdemo.bean.HttpResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by wanglijuan on 2017/6/22.
 */

public interface ApiService {
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST(ApiUrl.LOGIN)
    Observable<HttpResult<UserBean>> login(@Body RequestBody request);

    @POST(ApiUrl.REGISTER)
    Observable<HttpResult<UserBean>> register(@Body RequestBody request);
}
