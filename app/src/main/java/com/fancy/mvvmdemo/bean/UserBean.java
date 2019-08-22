package com.fancy.mvvmdemo.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @author pengkuanwang
 * @date 2019-08-19
 */
public class UserBean implements Serializable {
    private String token;
    private String nickName;
    private String mobileNo;
    private String imageUrl;
    private String userId;
    /**
     * 快捷登录用户是否拥有密码
     */
    private boolean hasPwd;
    /**
     * 是否需要设置密码
     */
    private boolean isSetPwd;
    private String cycode;
    /**
     * 是否有消息
     */
    private boolean hasMsg;
    /**
     * 是否有支付密码
     */
    private boolean hasPayPwd;
    /**
     * 是否是新用户(00:老用户，01：新用户)
     */
    private String userFlag;

    public UserBean(String token, String nickName) {
        this.token = token;
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getHasPwd() {
        return hasPwd;
    }

    public void setHasPwd(boolean hasPwd) {
        this.hasPwd = hasPwd;
    }

    public boolean isHasMsg() {
        return hasMsg;
    }

    public void setHasMsg(boolean hasMsg) {
        this.hasMsg = hasMsg;
    }

    public boolean isHasPayPwd() {
        return hasPayPwd;
    }

    public void setHasPayPwd(boolean hasPayPwd) {
        this.hasPayPwd = hasPayPwd;
    }

    public boolean getIsSetPwd() {
        return isSetPwd;
    }

    public void setIsSetPwd(boolean isSetPwd) {
        this.isSetPwd = isSetPwd;
    }

    public String getCycode() {
        return cycode;
    }

    public void setCycode(String cycode) {
        this.cycode = cycode;
    }

    /**
     * 该用户是否是新用户
     *
     * @return
     */
    public boolean isNewUser() {
        return TextUtils.equals(userFlag, "01");
    }
}
