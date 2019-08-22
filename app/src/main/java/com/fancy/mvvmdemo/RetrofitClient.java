package com.fancy.mvvmdemo;

import com.fancy.mvvmdemo.http.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author pengkuanwang
 * @date 2019-08-22
 */
public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://test-m-stg.ppppoints.com/";
    private static final int CONNECT_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 300;
    private static final int READ_TIMEOUT = 30;

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public RetrofitClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(new LoggingInterceptor());
        }
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }
}
