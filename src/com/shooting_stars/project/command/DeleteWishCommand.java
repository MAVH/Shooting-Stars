package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
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
public class DeleteWishCommand extends ActionSupport {
    static Logger logger = Logger.getLogger(SaveWishesCommand.class);
    private Exception exception;
    private int wishId;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DeleteWishCommand.logger = logger;
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

    public String execute() {
        String result = SUCCESS;
        try {
            WishLogic.deleteWish(wishId);
        } catch (LogicException e) {
            logger.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
