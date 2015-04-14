package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.Message;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.MessageLogic;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class GetMessagesCommand extends SessionAwareCommand {
    private int chatId;
    private int page = 1;
    private List<Message> messages;
    private int messagesAmount;


    int currentUserId;
    public void validate() {
        currentUserId = getCurrentUserId();
        //проверка, является ли текущий пользователь участником чата
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            if(messagesAmount == 0) {
                messagesAmount = MessageLogic.getMessagesAmount(chatId);
            }
            messages = MessageLogic.getMessages(chatId, currentUserId, page);
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMessagesAmount() {
        return messagesAmount;
    }

    public void setMessagesAmount(int messagesAmount) {
        this.messagesAmount = messagesAmount;
    }
}
