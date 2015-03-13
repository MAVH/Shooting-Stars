package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 04.03.2015.
 */
public class FormUserPageCommand extends ActionSupport {
    static Logger logger = Logger.getLogger(FormUserPageCommand.class);
    @Override
    public String execute() {

        //String page = ConfigManager.getProperty("path.page.user");
        return SUCCESS;
    }
}
