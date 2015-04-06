package com.shooting_stars.project.entity;

import com.shooting_stars.project.exception.HashingException;
import com.shooting_stars.project.hashing.MD5Hashing;

import java.sql.Date;

public class UserToBeRegistered {
    private String password;
    private UserInfo info;
    private String[] wishes;
    public final int MAX_AMOUNT_OF_WISHES = 5;

    public UserToBeRegistered() {
        info = new UserInfo();
        wishes = new String[MAX_AMOUNT_OF_WISHES];
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void hashPassword() throws HashingException {
        password = MD5Hashing.hashingPassword(password);
    }

    public String getLogin() {
        return info.getLogin();
    }

    public String getEmail() {
        return info.getEmail();
    }

    public String getName() {
        return info.getName();
    }

    public String getSurname() {
        return info.getSurname();
    }

    public String getCountry() {
        return info.getCountry();
    }

    public String getCity() {
        return info.getCity();
    }

    public Date getDateOfBirth() {
        return info.getDateOfBirth();
    }

    public String getAbilities() {
        return info.getAbilities();
    }

    public void setLogin(String login) {
        info.setLogin(login);
    }

    public void setEmail(String email) {
        info.setEmail(email);
    }

    public void setName(String name) {
        info.setName(name);
    }

    public void setSurname(String surname) {
        info.setSurname(surname);
    }

    public void setCountry(String country) {
        info.setCountry(country);
    }

    public void setCity(String city) {
        info.setCity(city);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        info.setDateOfBirth(dateOfBirth);
    }

    public void setAbilities(String abilities) {
        info.setAbilities(abilities);
    }

    public String getPhotoName() {
        return info.getPhotoName();
    }

    public void setPhotoName(String photoName) {
        info.setPhotoName(photoName);
    }

    public String[] getWishes() {
        return wishes;
    }

    public void setWishes(String[] wishes) {
        this.wishes = wishes;
    }

}
