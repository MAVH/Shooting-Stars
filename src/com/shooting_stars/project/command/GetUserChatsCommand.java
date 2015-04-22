package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.Chat;
import com.shooting_stars.project.entity.Message;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class GetUserChatsCommand extends SessionAwareCommand {

    private List<Chat> chats;
    private int page = 1;
    private int chatsAmount;

    @Override
    public String execute() {
        String result = SUCCESS;
        int currentUserId = getCurrentUserId();
        try {
            if(chatsAmount == 0) {
                chatsAmount = MessageLogic.getChatsAmount(currentUserId);
            }
            chats = MessageLogic.getUserChats(currentUserId, page);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getChatsAmount() {
        return chatsAmount;
    }

    public void setChatsAmount(int chatsAmount) {
        this.chatsAmount = chatsAmount;
    }
}
