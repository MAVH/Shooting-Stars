package com.shooting_stars.project.command;

import com.shooting_stars.project.manager.ConfigManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return ConfigManager.getProperty("path.page.index");
    }
}
