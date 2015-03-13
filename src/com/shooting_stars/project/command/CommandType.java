package com.shooting_stars.project.command;

public enum CommandType {
    //LOGIN(new LoginCommand()),
    //LOGOUT(new LogoutCommand()),
    //PREPARE_REGISTRATION(new PreparingRegistrationCommand()),
    //CHANGE_LOCALE(new ChangeLocaleCommand()),
    CHANGE_PHOTO(new ChangePhotoCommand());
    //FORM_USER_PAGE(new FormUserPageCommand());
    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
