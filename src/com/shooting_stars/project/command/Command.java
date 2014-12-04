package com.shooting_stars.project.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 07.10.2014.
 */
public interface Command {
    String execute(HttpServletRequest request);
}
