package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserInfo;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class SaveEditedUserInfoCommand extends ActionSupport implements SessionAware, ModelDriven<UserInfo> {

    private Exception exception;
    private UserInfo userInfo;
    private Map<String, Object> sessionAttributes = null;

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            int userId = ((User) sessionAttributes.get("user")).getUserId();
            UserLogic.updateUserInfo(userId, userInfo);
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

    @Override
    public UserInfo getModel() {
        return userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}