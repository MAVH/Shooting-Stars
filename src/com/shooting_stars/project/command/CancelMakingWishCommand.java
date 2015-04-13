package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;



public class CancelMakingWishCommand extends SessionAwareCommand {
    private int wishId;

    @Override
    public String execute() {
        String result = SUCCESS;
        int currentUserId = getCurrentUserId();
        try {

            int userId = WishLogic.cancelWishMaking(wishId);
            //change message
            MessageLogic.sendMessage(currentUserId, userId, "cancel making wish");
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }
}
