package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.dao.WishDAO;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 14.03.2015.
 */
public class SaveWishesCommand extends ActionSupport {
    static Logger logger = Logger.getLogger(SaveWishesCommand.class);
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
        HttpServletRequest request = ServletActionContext.getRequest();
        String[] wishes = request.getParameterValues(PARAM_WISH);
        int userId = ((User)request.getSession().getAttribute("user")).getUserId();
        try {
            WishLogic.addWishes(userId,wishes);
        } catch (LogicException e) {
            logger.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
