package com.shooting_stars.project.command;


import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class GoToChatCommand extends SessionAwareCommand {
    private int chatId;
    private int userId;
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            int currentUserId = getCurrentUserId();
            chatId = MessageLogic.getChatId(currentUserId,userId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;

    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
