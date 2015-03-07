package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 04.03.2015.
 */
public class FormUserPageCommand extends ActionSupport {
    @Override
    public String execute() {

        //String page = ConfigManager.getProperty("path.page.user");
        return SUCCESS;
    }
}
