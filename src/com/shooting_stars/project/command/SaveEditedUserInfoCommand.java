package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserInfo;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.Date;
import java.util.Map;

public class SaveEditedUserInfoCommand extends ActionSupport implements SessionAware, ModelDriven<UserInfo> {

    private Exception exception;
    private String date;
    private UserInfo userInfo = new UserInfo();
    private String emptyFieldsMessage;
    private Map<String, Object> sessionAttributes = null;
    @Override
    public void validate() {
        if(StringUtils.isEmpty(userInfo.getName())) {
            addFieldError("name", Controller.messageManager.getMessage("message.fields.empty"));
        }
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        try {

            int userId = ((User) sessionAttributes.get("user")).getUserId();
            if(!StringUtils.isEmpty(date)) {
                userInfo.setDateOfBirth(Date.valueOf(date));
            }
            UserLogic.updateUserInfo(userId, userInfo);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            result = ERROR;
            exception = new CommandException(e.getCause());
        }
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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