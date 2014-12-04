package com.shooting_stars.project.command;

import com.shooting_stars.project.logic.LoginLogic;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.manager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


/**
 * Created by Пользователь on 07.10.2014.
 */
public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);
        if(loginValue == null || loginValue.isEmpty() || passValue == null || passValue.isEmpty()) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("message.login.empty", (Locale) request.getSession().getAttribute("locale")));
            page = ConfigManager.getProperty("path.page.login");
        }  else {
            if (LoginLogic.checkUser(loginValue, passValue)) {
                page = ConfigManager.getProperty("path.page.main");
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("message.loginerror", (Locale) request.getSession().getAttribute("locale")));
                page = ConfigManager.getProperty("path.page.login");
            }
        }
        return page;
    }
}
