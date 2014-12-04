package com.shooting_stars.project.command;

/**
 * Created by Пользователь on 07.10.2014.
 */
public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
