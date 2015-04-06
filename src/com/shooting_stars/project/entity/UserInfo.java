package com.shooting_stars.project.entity;

import java.sql.Date;

public class UserInfo {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String country;
    private String city;
    private Date dateOfBirth;
    private String abilities;
    private String photoName;

    public String getLogin() {
        return login;
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

    public String getAbilities() {
        return abilities;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
