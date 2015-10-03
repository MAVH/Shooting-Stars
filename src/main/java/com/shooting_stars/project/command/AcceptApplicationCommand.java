package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;
import com.shooting_stars.project.manager.MessageManager;

public class AcceptApplicationCommand extends SessionAwareCommand {
    private int wishId;
    private int userId;
    @Override
    public String execute() {
        String result = SUCCESS;
        int currentUserId = getCurrentUserId();
        MessageManager messageManager = (MessageManager)sessionAttributes.get("messageManager");
        try {
            MessageLogic.sendMessage(currentUserId, userId,messageManager.getMessage("message.wish.application.accepted"));
            WishLogic.takeApplication(wishId,userId);
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
