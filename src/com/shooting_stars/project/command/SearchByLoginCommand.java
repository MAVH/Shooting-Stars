package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.SearchLogic;

import java.util.ArrayList;

/**
 * Created by Пользователь on 02.04.2015.
 */
public class SearchByLoginCommand extends Command {
    private String login;
    private ArrayList<User> foundUsers;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<User> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(ArrayList<User> foundUsers) {
        this.foundUsers = foundUsers;
    }

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            /*
            foundUsers = new ArrayList<User>();
            foundUsers.add(SearchLogic.findSingleUserByLogin(login));
            */
            foundUsers = SearchLogic.findUsersByLogin(login);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
