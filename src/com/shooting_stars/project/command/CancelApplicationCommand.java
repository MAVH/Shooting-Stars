package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Пользователь on 19.03.2015.
 */
public class CancelApplicationCommand extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {
    private Exception exception;
    private int wishId;
    private int userId;
    private static final String USER = "user";
    private static final String OTHER_USER = "other_user";
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
    private HttpServletResponse response = null;
    private HttpServletRequest request = null;
    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        User currentUser = (User)sessionAttributes.get("user");
        int currentUserId = currentUser.getUserId();

        try {
            int id = WishLogic.cancelApplication(wishId,userId);
            if(currentUserId == userId) {
                userId = id;
                sessionAttributes.put("userId",id);
                //request.setAttribute("userId",id);
            }
            //change message
            MessageLogic.sendMessage(currentUserId, userId, "message");
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
