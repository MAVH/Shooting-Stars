package com.shooting_stars.project.entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Пользователь on 14.03.2015.
 */
public class Wish implements Iterable{
    private int wishId;
    private String wish;
    private boolean isPerformed;
    private ArrayList<User> candidates;
    private User candidate;

    public Wish(int wishId, String wish) {
        this.wishId = wishId;
        this.wish = wish;
    }

    public int getWishId() {
        return wishId;
    }

    public Wish(int wishId, String wish, ArrayList<User> candidates) {
        this.wishId = wishId;
        this.candidates = candidates;
        this.wish = wish;
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

    public boolean isPerformed() {
        return isPerformed;
    }

    public void setPerformed(boolean isPerformed) {
        this.isPerformed = isPerformed;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    @Override
    public Iterator iterator() {
        return candidates.iterator();
    }
}
