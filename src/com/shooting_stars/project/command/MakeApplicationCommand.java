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
public class MakeApplicationCommand extends ActionSupport implements SessionAware {
    private Map<String, Object> sessionAttributes = null;
    private Exception exception;
    private int wishId;



    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }

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
    @Override
    public String execute() {
        String result = SUCCESS;
        User currentUser = (User)sessionAttributes.get("user");
        int currentUserId = currentUser.getUserId();
        try {
            int userId = WishLogic.makeApplication(wishId, currentUserId);
            //change message
            MessageLogic.sendMessage(currentUserId, userId, "message");
            sessionAttributes.put("userId",userId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
