package com.fancy.mvvmdemo.http;

import com.fancy.mvvmdemo.BuildConfig;
import com.fancy.mvvmdemo.util.Url;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class Api {
    public static ApiService SERVICE;
    private static final int CONNECT_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 300;
    private static final int READ_TIMEOUT = 30;

    public static ApiService getDefault() {
        if (SERVICE == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
            if (!BuildConfig.DEBUG) {
                httpClientBuilder.addInterceptor(new LoggingInterceptor());
            }
            SERVICE = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Url.BASE_URL)
                    .build().create(ApiService.class);
        }
        return SERVICE;
    }
}
