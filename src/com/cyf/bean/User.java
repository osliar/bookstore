package com.cyf.bean;

public class User {

    private Integer userId;//用户id
    private Integer userLevelId;//用户权限等级
    private String userName;//用户名
    private String userPassword;//密码

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

	public Object getSessionid() {
		// TODO Auto-generated method stub
		return null;
	}
}
