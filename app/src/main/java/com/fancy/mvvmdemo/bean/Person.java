package com.fancy.mvvmdemo.bean;

import android.databinding.ObservableField;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class Person {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Integer> age = new ObservableField<>();
    public ObservableField<String> gender = new ObservableField<>();

}
