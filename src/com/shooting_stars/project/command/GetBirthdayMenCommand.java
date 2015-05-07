package com.shooting_stars.project.command;


import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.SearchLogic;

import java.util.List;

public class GetBirthdayMenCommand extends Command {
    private List<User> foundUsers;
    private int page = 1;
    private int usersAmount;

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            foundUsers = SearchLogic.findBirthdayMen(page);
            usersAmount = SearchLogic.countBirthdayMen();
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<User> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<User> foundUsers) {
        this.foundUsers = foundUsers;
    }

    public int getUsersAmount() {
        return usersAmount;
    }

    public void setUsersAmount(int usersAmount) {
        this.usersAmount = usersAmount;
    }
}
