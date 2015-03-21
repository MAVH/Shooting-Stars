package com.shooting_stars.project.entity;

import com.shooting_stars.project.exception.HashingException;
import com.shooting_stars.project.hashing.MD5Hashing;

public class UserToBeRegistered extends OtherUser {
    private String password;

    public UserToBeRegistered() {
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

}
