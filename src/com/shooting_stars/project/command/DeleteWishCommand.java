package com.shooting_stars.project.command;


import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;
import com.shooting_stars.project.manager.MessageManager;


public class DeleteWishCommand extends SessionAwareCommand {
    private int wishId;
    private String messageError;

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            if(!WishLogic.deleteWish(wishId)) {
                 messageError = ((MessageManager)sessionAttributes.get("messageManager")).getMessage("message.delete.impossible");
            }
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
}
