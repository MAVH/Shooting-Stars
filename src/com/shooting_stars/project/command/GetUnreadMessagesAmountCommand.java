package com.shooting_stars.project.command;


import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;

public class GetUnreadMessagesAmountCommand extends SessionAwareCommand {
    private int unreadMessagesAmount;
    public String execute() {
        String result = SUCCESS;
        int userId = getCurrentUserId();
        try {
            unreadMessagesAmount = MessageLogic.getUnreadMessagesAmount(userId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public int getUnreadMessagesAmount() {
        return unreadMessagesAmount;
    }

    public void setUnreadMessagesAmount(int unreadMessagesAmount) {
        this.unreadMessagesAmount = unreadMessagesAmount;
    }
}
