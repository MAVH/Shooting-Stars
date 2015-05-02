package com.shooting_stars.project.command;


import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;

public class GetEmailCommand extends SessionAwareCommand {
    private String email;
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            email = UserLogic.getEmail(getCurrentUserId());
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
