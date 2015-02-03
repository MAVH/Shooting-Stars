package com.shooting_stars.project.command;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public static Command defineCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        Command currentCommand;
        if(commandName == null) {
            currentCommand = new EmptyCommand();
        } else {
            try {
                CommandType type = CommandType.valueOf(commandName.toUpperCase());
                currentCommand = type.getCommand();
            } catch (IllegalArgumentException e) {
                currentCommand = new EmptyCommand();
            }
        }
        return currentCommand;
    }
}
