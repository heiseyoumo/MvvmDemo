package com.fancy.mvvmdemo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MD5 {

    private MD5() {
    }

    public final static String getMessageDigest(String param) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(param.getBytes());
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String genSign(HashMap<String, String> params) {
        //将参数以参数名的字典升序排序
        Map<String, String> sortParams = new TreeMap<>(params);
        Set<Map.Entry<String, String>> entrys = sortParams.entrySet();

        //遍历排序的字典,并拼接"key=value"格式
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, String> entry : entrys) {
            if (entry.getValue() != null) {
                baseString.append(entry.getKey()).append(entry.getValue());
            }
        }
        //使用MD5对待签名串求签
        byte[] bytes = null;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            try {
                bytes = md5.digest(baseString.toString().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }
}
