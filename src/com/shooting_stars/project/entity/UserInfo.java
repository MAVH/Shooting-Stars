package com.shooting_stars.project.entity;

import java.sql.Date;

public class UserInfo extends User {
    //private String login;
    private String email;
   // private String name;
   // private String surname;
    private String country;
    private String city;
    private Date dateOfBirth;
    private String abilities;
    //private String photoName;

    public String getEmail() {
        return email;
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

    public void setEmail(String email) {
        this.email = email;
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
}
