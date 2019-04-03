package com.entity;


import java.util.Date;

public class User1 {


    private int uId;
    private String  username;
    private String  password;
    private String  roleName;
    private String  user;
    private String  userState;
    private Date loginDate;
    public User1(int uId, String username, String password) {
        this.uId = uId;
        this.username = username;
        this.password = password;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User1(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User1(String username, String password, String user, String userState) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.userState = userState;
    }

    public User1(int uId, String username, String password, String user, String userState) {
        this.uId = uId;
        this.username = username;
        this.password = password;
        this.user = user;
        this.userState = userState;
    }

    public User1(int uId, String username, String password, String user, String userState, Date loginDate) {
        this.uId = uId;
        this.username = username;
        this.password = password;
        this.user = user;
        this.userState = userState;
        this.loginDate = loginDate;
    }

    public User1() {
    }
}
