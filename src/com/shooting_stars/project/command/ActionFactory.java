package com.shooting_stars.project.command;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public static Command defineCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        Command current = null;
        if(commandName == null) {
            return new EmptyCommand();
        }
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = type.getCommand();
        } catch (IllegalArgumentException e) {
            current = new EmptyCommand();
        }
        return current;
    }
}
