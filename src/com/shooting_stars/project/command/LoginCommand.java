package com.shooting_stars.project.command;

import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.LoginLogic;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.manager.MessageManager;
import com.shooting_stars.project.validation.Validation;
import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passwordValue = request.getParameter(PARAM_PASSWORD);
        if(Validation.isEmpty(loginValue) || Validation.isEmpty(passwordValue)) {
            request.setAttribute("loginOrPasswordErrorMessage", Controller.messageManager.getMessage("message.fields.empty"));
            page = ConfigManager.getProperty("path.page.login");
        }  else {
            try {
                User user = LoginLogic.checkUser(loginValue, passwordValue);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    page = ConfigManager.getProperty("path.page.main");
                } else {
                    request.setAttribute("loginOrPasswordErrorMessage", Controller.messageManager.getMessage("message.login.error"));
                    page = ConfigManager.getProperty("path.page.login");
                }
            } catch (LogicException e) {
                throw new CommandException(e.getCause());
            }
        }
        return page;
    }
}
