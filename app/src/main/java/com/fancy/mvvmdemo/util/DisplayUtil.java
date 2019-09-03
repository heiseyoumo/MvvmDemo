package com.fancy.mvvmdemo.util;

import android.content.res.Resources;

import javax.inject.Inject;

/**
 * @author pengkuanwang
 * @date 2019-09-03
 */
public class DisplayUtil {

    @Inject
    public DisplayUtil() {

    }

    /**
     * Converts dp size into pixels.
     *
     * @param dp dp size to get converted
     * @return Pixel size
     */
    public static float dip2px(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }
}
