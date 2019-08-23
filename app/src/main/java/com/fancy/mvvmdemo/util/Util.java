package com.fancy.mvvmdemo.util;

import com.fancy.mvvmdemo.BuildConfig;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
/**
 * @author pengkuanwang
 * @date 2018/1/15
 */
public class Util {
    private static void getCommonParamsAndSign(HashMap<String, String> map) {
        map.put("appId", "app");
        map.put("authType", "MD5");
        map.put("custString", "app");
        map.put("msgVersion", BuildConfig.VERSION_NAME);
        map.put("platform", "android");
        map.put("reqTime", String.valueOf(System.currentTimeMillis()));
        //ip由服务端来取
        map.put("ip", "0.0.0.0");
        String sign = getSign(map);
        map.put("sign", sign);
    }

    public static RequestBody getRequest(HashMap<String, String> params) {
        Util.getCommonParamsAndSign(params);
        String jsonParams = new Gson().toJson(params);
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), jsonParams);
    }

    /**
     * 获取新的秘钥
     *
     * @return
     */
    private static String getSign(HashMap<String, String> map) {
        String key = "app" + MD5.genSign(map).toUpperCase() + BuildConfig.VERSION_NAME;
        String secretKey = MD5.getMessageDigest(key);
        return secretKey;
    }
}
