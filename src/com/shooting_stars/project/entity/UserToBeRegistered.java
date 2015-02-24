package com.shooting_stars.project.entity;

import com.shooting_stars.project.exception.HashingException;
import com.shooting_stars.project.hashing.MD5Hashing;

import java.io.InputStream;
import java.sql.Date;

public class UserToBeRegistered {
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String country;
    private String city;
    private Date dateOfBirth;
    private InputStream photo;
    private String abilities;
    private String[] wishes;
    private final int MAX_AMOUNT_OF_WISHES = 5;

    public UserToBeRegistered() {
        wishes = new String[MAX_AMOUNT_OF_WISHES];
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public String getAbilities() {
        return abilities;
    }

    public String[] getWishes() {
        return wishes;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public void setWishes(String[] wishes) {
        this.wishes = wishes;
    }

    public void hashPassword() throws HashingException {
        password = MD5Hashing.hashingPassword(password);
    }
}
