package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.dao.WishDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class SaveWishesCommand extends ActionSupport implements ServletRequestAware, SessionAware {
    private HttpServletRequest request = null;
    private Map<String, Object> sessionAttributes = null;
    private static final String PARAM_WISH = "wish";
    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        String[] wishes = request.getParameterValues(PARAM_WISH);
        int userId = ((User)sessionAttributes.get("user")).getUserId();
        try {
            WishLogic.addWishes(userId,wishes);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
           this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.sessionAttributes = stringObjectMap;
    }
}
