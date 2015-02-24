package com.shooting_stars.project.entity;

public class User {
    private int userId;
    private String login;

    public User(int userId, String login) {
        this.userId = userId;
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
