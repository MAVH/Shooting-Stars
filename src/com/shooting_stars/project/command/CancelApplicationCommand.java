package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;

public class CancelApplicationCommand extends SessionAwareCommand {
    private int wishId;
    private int userId; //чью за€вку отмен€ют
    private int pageCode;
    private int sessionUserId;
    @Override
    public String execute() {
        String result = SUCCESS;
        sessionUserId = getCurrentUserId();
        int receiverId;
        try {
            int applicantId = userId;
            int wishOwnerId = WishLogic.cancelApplication(wishId, applicantId);
            if(sessionUserId == applicantId) {
                if(pageCode != 0) {
                    result = "myPage";
                }
                receiverId = wishOwnerId;
                userId = wishOwnerId;
            } else {
                receiverId = applicantId;
                userId = sessionUserId;
            }
            //TODO change message
            MessageLogic.sendMessage(sessionUserId, receiverId, "cancel application");
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
