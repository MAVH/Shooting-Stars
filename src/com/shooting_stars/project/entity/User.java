package com.shooting_stars.project.entity;

public class User {
    private int userId;
    private String login;
    private String name;
    private String surname;
    private String photoName;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(int userId, String login) {
        this.userId = userId;
        this.login = login;
    }

    public User(int userId, String name, String surname) {
        this(userId);
        this.name = name;
        this.surname = surname;
    }

    public User(int userId, String name, String surname, String photoName) {
        this(userId,name,surname);
        this.photoName = photoName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (userId != user.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        return result;
    }
}
