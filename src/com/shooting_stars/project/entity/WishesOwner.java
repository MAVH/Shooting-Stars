package com.shooting_stars.project.entity;


import java.util.ArrayList;
import java.util.List;

public class WishesOwner {
    User owner;
    private List<Wish> wishes;

    public WishesOwner(User owner) {
        this.owner = owner;
        wishes = new ArrayList<Wish>();
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    public void addWish(Wish wish) {
        wishes.add(wish);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WishesOwner)) return false;

        WishesOwner owner1 = (WishesOwner) o;

        if (owner != null ? !owner.equals(owner1.owner) : owner1.owner != null) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        return result;
    }
}
