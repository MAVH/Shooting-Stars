package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;
import com.shooting_stars.project.manager.MessageManager;

public class CancelApplicationCommand extends SessionAwareCommand {
    private int wishId;
    private int userId; //чью за€вку отмен€ют
    private int pageCode;
    private int sessionUserId;
    @Override
    public String execute() {
        String result = "profile";
        sessionUserId = getCurrentUserId();
        int receiverId;
        MessageManager messageManager = (MessageManager)sessionAttributes.get("messageManager");
        String message;
        try {
            int applicantId = userId;
            int wishOwnerId = WishLogic.cancelApplication(wishId, applicantId);
            if(sessionUserId == applicantId) {
                message = messageManager.getMessage("message.wish.application.owner.cancel");
                if(pageCode != 0) {
                    result = "myWishesPage";
                } else {
                    result = "userPage";
                }
                receiverId = wishOwnerId;
                userId = wishOwnerId;
            } else {
                receiverId = applicantId;
                userId = sessionUserId;
                message = messageManager.getMessage("message.wish.application.applier.cancel");
            }
            MessageLogic.sendMessage(sessionUserId, receiverId, message);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
    public int getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(int sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
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
