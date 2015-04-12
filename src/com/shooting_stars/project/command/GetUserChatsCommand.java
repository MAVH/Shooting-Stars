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

public class GetUserChatsCommand extends ActionSupport implements SessionAware {
    private Exception exception;
    private Map<String, Object> sessionAttributes = null;
    private List<Chat> chats;

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        User currentUser = (User)sessionAttributes.get("user");
        try {
            chats = MessageLogic.getUserChats(currentUser.getUserId());
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }
}
