package com.shooting_stars.project.command;


import com.shooting_stars.project.entity.WishesOwner;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.SearchLogic;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindWishesCommand extends Command {
    private String wish;
    private List<WishesOwner> foundUsers;
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            foundUsers = SearchLogic.findWishes(wish);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public List<WishesOwner> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<WishesOwner> foundUsers) {
        this.foundUsers = foundUsers;
    }
}
