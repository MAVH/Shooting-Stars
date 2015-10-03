package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;

public class UpdateVisitTimeCommand extends SessionAwareCommand {

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            int currentUserId = getCurrentUserId();
            UserLogic.updateVisitTime(currentUserId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
        }
}
