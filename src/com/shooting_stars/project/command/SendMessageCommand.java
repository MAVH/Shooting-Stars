package com.shooting_stars.project.command;


import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class SendMessageCommand extends ActionSupport implements SessionAware {
    private String message;
    private int chatId;
    private Exception exception;
    private Map<String, Object> sessionAttributes = null;

    @Override
    public void validate() {
        if(StringUtils.isEmpty(message)) {
            addFieldError("message", "Empty message wasn't sent");
        }
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            int currentUserId = ((User)sessionAttributes.get("user")).getUserId();
            MessageLogic.sendMessage(chatId, message, currentUserId);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }


    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
