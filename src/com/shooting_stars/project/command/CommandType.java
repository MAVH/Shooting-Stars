package com.shooting_stars.project.command;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    PREPARE_REGISTRATION(new PreparingRegistrationCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
