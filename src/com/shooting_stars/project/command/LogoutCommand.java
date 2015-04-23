package com.shooting_stars.project.command;


public class LogoutCommand extends SessionAwareCommand {
    private static final String PARAM_CURRENT_USER_ID = "currentUserId";
    @Override
    public String execute() {
       sessionAttributes.remove(PARAM_CURRENT_USER_ID);
       return SUCCESS;
    }
}
