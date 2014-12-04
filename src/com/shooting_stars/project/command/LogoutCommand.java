package com.shooting_stars.project.command;

import com.shooting_stars.project.manager.ConfigManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 07.10.2014.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return ConfigManager.getProperty("path.page.index");
    }
}
