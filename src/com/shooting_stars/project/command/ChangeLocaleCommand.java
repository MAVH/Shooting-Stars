package com.shooting_stars.project.command;

import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.manager.LocaleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private final String PARAM_LOCALE = "locale";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String localeValue = request.getParameter(PARAM_LOCALE);
        HttpSession session = request.getSession();
        Locale locale = LocaleManager.valueOf(localeValue).getLocale();
        Controller.messageManager.changeLocale(locale);
        session.setAttribute("locale", locale);
        Controller.messageManager.getMessage("message.login.error");
        //TODO: return current page
        return ConfigManager.getProperty("path.page.login");
    }
}
