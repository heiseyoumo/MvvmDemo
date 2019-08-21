package com.fancy.mvvmdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fancy.mvvmdemo.bean.HttpResult;
import com.fancy.mvvmdemo.http.Api;
import com.fancy.mvvmdemo.http.ProgressSubscriber;
import com.fancy.mvvmdemo.http.UserBean;
import com.fancy.mvvmdemo.util.HttpUtil;
import com.fancy.mvvmdemo.util.Util;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hehe");
            }
        });
        Observable<String> compose = observable.compose(new ObservableTransformer<String, String>() {

            @Override
            public ObservableSource<String> apply(Observable<String> upstream) {
                System.out.println(upstream);
                return upstream;
            }
        });
        compose.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("链接成功");
            }

            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> hashMap = new HashMap<>();
                Observable<HttpResult<UserBean>> observable = Api.getDefault().login(Util.getRequest(hashMap));
                HttpUtil.getInstance().toSubscribe(observable, new ProgressSubscriber<UserBean>(MainActivity.this) {

                    @Override
                    protected void _onNext(UserBean userBean) {
                        String name = userBean.getName();
                        System.out.println(name);
                    }

                    @Override
                    protected void _onError(String code, String message) {
                        System.out.println(code);
                    }
                });
            }
        });
    }
}
