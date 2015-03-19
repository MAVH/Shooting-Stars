package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by Пользователь on 19.03.2015.
 */
public class MarkWishMadeCommand extends ActionSupport implements SessionAware {
    private Exception exception;
    private int wishId;

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

            int userId = WishLogic.markWishMade(wishId);
            //change message
            MessageLogic.sendMessage(currentUser.getUserId(), userId, "message");
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
