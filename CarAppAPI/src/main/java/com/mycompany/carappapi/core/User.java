/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.core;

import java.util.List;

/**
 *
 * @author User
 */
public class User {
    private String userID;
    private String userName;
    private String phoneNumber;
    private List<CarInfo> userInfo;
    public String getUserID() {
        return userID;
    }

    public void setUserID(String username) {
        this.userID = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CarInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<CarInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
