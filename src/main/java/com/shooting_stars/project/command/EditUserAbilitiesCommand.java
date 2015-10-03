package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class EditUserAbilitiesCommand extends SessionAwareCommand {

    private String abilities;

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            int currentUserId = getCurrentUserId();
            abilities = UserLogic.getUserAbilities(currentUserId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            result = ERROR;
            exception = new CommandException(e.getCause());
        }
        return result;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

}