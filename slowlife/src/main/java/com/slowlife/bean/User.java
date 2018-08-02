package com.slowlife.bean;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Ignore;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "user")
public class User extends Model {


    private String userPassword;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userPicture;
    private int userNumber;
    private int userVerification;//是否验证，已验证为1，未验证为-1；


    public int getUserVerification() {
        return userVerification;
    }

    public void setUserVerification(int userVerification) {
        this.userVerification = userVerification;
    }

    /**
     * 不需要理会，前端可直接删除此字段
     */
    @Ignore
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User() {
    }

    public User(String userPassword, String userName, String userPhone, String userEmail, int userNumber) {
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
    }

    public void setUserPicture(String picture){userPicture=picture;}

    public String getUserPicture(){return userPicture;}

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }
}
