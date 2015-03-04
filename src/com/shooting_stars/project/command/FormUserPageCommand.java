package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 04.03.2015.
 */
public class FormUserPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return ConfigManager.getProperty("path.page.user");
    }
}
