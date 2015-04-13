package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

public class GetWishesUserParticipatesInCommand extends SessionAwareCommand {
    private int userId;
    private ArrayList<Wish> wishes;
    private boolean isPageOwner;

    public boolean getIsPageOwner() {
        return isPageOwner;
    }

    public void setPageOwner(boolean isOwner) {
        this.isPageOwner = isOwner;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(ArrayList<Wish> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            int currentUserId = getCurrentUserId();
            if(currentUserId == userId) {
                isPageOwner = true;
            } else {
                isPageOwner = false;
            }
            wishes = WishLogic.getWishesByMakerId(userId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
