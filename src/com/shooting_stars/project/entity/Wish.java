package com.shooting_stars.project.entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Пользователь on 14.03.2015.
 */
public class Wish implements Iterable{
    private int wishId;
    private String wish;
    private ArrayList<Candidate> candidates;

    public Wish(int wishId, String wish) {
        this.wishId = wishId;
        this.wish = wish;
    }

    public int getWishId() {
        return wishId;
    }

    public Wish(int wishId, String wish, ArrayList<Candidate> candidates) {
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

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public Iterator iterator() {
        return candidates.iterator();
    }
}
