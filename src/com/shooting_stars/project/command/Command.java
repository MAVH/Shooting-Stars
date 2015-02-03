package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
