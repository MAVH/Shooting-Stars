package com.shooting_stars.project.command;


import com.shooting_stars.project.entity.WishesOwner;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.SearchLogic;

import java.util.List;

public class FindRecentWishesCommand extends Command {

    private List<WishesOwner> foundUsers;
    private int amount;
    private int page = 1;
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            foundUsers = SearchLogic.findWishes("",page);
            amount = SearchLogic.countWishes("");
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public List<WishesOwner> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<WishesOwner> foundUsers) {
        this.foundUsers = foundUsers;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
