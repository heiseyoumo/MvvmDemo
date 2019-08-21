package com.fancy.mvvmdemo.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fancy.mvvmdemo.MyApplication;
import com.fancy.mvvmdemo.R;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class ImageLoadUtil {

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(MyApplication.getInstance()).load(url).placeholder(R.mipmap.huanzhe).into(imageView);
    }
}
