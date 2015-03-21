package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class AcceptApplicationCommand extends ActionSupport implements SessionAware {
    private Exception exception;
    private int wishId;
    private int userId;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private Map<String, Object> sessionAttributes = null;
    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
         sessionAttributes = stringObjectMap;
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        User currentUser = (User)sessionAttributes.get("user");
        try {
              //TODO change message
            //change message
            MessageLogic.sendMessage(currentUser.getUserId(),userId,"message");
            WishLogic.takeApplication(wishId,userId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
