package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;
import com.shooting_stars.project.manager.MessageManager;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;


public class DeleteWishCommand extends ActionSupport implements SessionAware {
    private Exception exception;
    private int wishId;
    private String messageError;
    private Map<String, Object> sessionAttributes = null;

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            if(!WishLogic.deleteWish(wishId)) {
                 messageError = ((MessageManager)sessionAttributes.get("messageManager")).getMessage("message.delete.impossible");
            }
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }
}
