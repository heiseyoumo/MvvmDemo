package com.fancy.mvvmdemo.bean;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class User {
    private String UserName;
    private String UserSex;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public User(String userName, String userSex) {
        UserName = userName;
        UserSex = userSex;
    }
}
