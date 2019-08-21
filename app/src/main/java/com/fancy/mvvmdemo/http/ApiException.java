package com.fancy.mvvmdemo.http;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class ApiException extends RuntimeException {
    private String code;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
