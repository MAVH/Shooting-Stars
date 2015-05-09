package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import com.shooting_stars.project.logic.WishLogic;



public class CancelMakingWishCommand extends SessionAwareCommand {

    private int wishId;
    private int userId;
    private int pageCode;
    private int sessionUserId;

    @Override
    public String execute() {
        String result = SUCCESS;
        sessionUserId = getCurrentUserId();
        //int currentUserId = getCurrentUserId();
        int receiverId;
        try {
            int applicantId = userId;
            int wishOwnerId = WishLogic.cancelWishMaking(wishId);
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
            //change message
            MessageLogic.sendMessage(sessionUserId, receiverId, "cancel making wish");
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

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public int getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(int sessionUserId) {
        this.sessionUserId = sessionUserId;
    }
}
