package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;
import com.shooting_stars.project.manager.ConfigManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Пользователь on 04.03.2015.
 */
public class FormUserPageCommand extends ActionSupport {
    static Logger logger = Logger.getLogger(FormUserPageCommand.class);
    private int userId;
    private Exception exception;
    ArrayList<Wish> wishes;
    private static final String USER = "user";
    private static final String OTHER_USER = "other_user";

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(ArrayList<Wish> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String execute() {
        String result = USER;
        try {
            wishes = WishLogic.getAllWishes(userId);
            HttpServletRequest request = ServletActionContext.getRequest();
            int id = ((User)request.getSession().getAttribute("user")).getUserId();
            if(userId != id) {
                result = OTHER_USER;
            }
        } catch (LogicException e) {
            logger.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }

        //String page = ConfigManager.getProperty("path.page.user");
        return result;
    }
}
