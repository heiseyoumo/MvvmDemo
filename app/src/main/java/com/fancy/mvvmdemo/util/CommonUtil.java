package com.fancy.mvvmdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author pengkuanwang
 * @date 2019-08-23
 */
public class CommonUtil {
    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (null == connManager) {
            return false;
        }
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (null == networkInfo || !networkInfo.isAvailable()
                || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
            return false;
        }
        return true;
    }
}
