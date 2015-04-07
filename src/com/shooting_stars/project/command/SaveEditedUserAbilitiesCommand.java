package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class SaveEditedUserAbilitiesCommand extends ActionSupport implements SessionAware {

    private Exception exception;
    private String abilities;
    private Map<String, Object> sessionAttributes = null;

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            int userId = ((User) sessionAttributes.get("user")).getUserId();
            UserLogic.updateUserAbilities(userId, abilities);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            result = ERROR;
            exception = new CommandException(e.getCause());
        }
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.sessionAttributes = stringObjectMap;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }
}