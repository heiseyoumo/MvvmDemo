package com.fancy.mvvmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author pengkuanwang
 * @date 2019-08-20
 */
public class Demo2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public Observable<String> processUrlIpByOneFlatMap(){
        return Observable.just(
                "http://www.baidu.com/",
                "http://www.google.com/",
                "https://www.bing.com/").flatMap(new Function<String, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(String s) throws Exception {
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
