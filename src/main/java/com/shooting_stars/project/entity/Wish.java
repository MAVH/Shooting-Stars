package com.shooting_stars.project.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class Wish /*implements Iterable*/ {
    private int wishId;
    private String wish;
    private boolean isFulfilled;
    private Date date;
    private User owner; //чье желание, нужно для вывода списка жеданий, которые выполнил текущий пользователь
    //У каждого желания присутствуют либо candidates, либо candidate
    private ArrayList<User> candidates; // те, кто подали заявку
    private User candidate; //тот, кто выполняет

    public Wish(int wishId, String wish) {
        this.candidates = new ArrayList<User>();
        this.wishId = wishId;
        this.wish = wish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public ArrayList<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<User> candidates) {
        this.candidates = candidates;
    }

    public boolean getIsFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }
    /*
    @Override
    public Iterator<User> iterator() {
        return candidates.iterator();
    }        */
}
