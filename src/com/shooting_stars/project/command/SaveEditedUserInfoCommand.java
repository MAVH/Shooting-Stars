package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ModelDriven;
import com.shooting_stars.project.entity.UserInfo;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import com.shooting_stars.project.manager.MessageManager;
import com.shooting_stars.project.validation.Validation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Date;

public class SaveEditedUserInfoCommand extends SessionAwareCommand implements ModelDriven<UserInfo> {

    private String date;
    private UserInfo userInfo = new UserInfo();
    private MessageManager messageManager;
    private String messageDateIncorrect;
    private String messageNameEmpty;
    @Override
    public void validate() {
        messageManager = (MessageManager)sessionAttributes.get("messageManager");
        if(StringUtils.isEmpty(userInfo.getName())) {
            messageNameEmpty = messageManager.getMessage("message.name.input");
            addFieldError("name", messageManager.getMessage("message.name.input"));

        }
        if(StringUtils.isNotEmpty(date)) {
            if (!Validation.isDateBeforeCurrent(Date.valueOf(date))) {
                messageDateIncorrect = messageManager.getMessage("message.date.more.current");
                addFieldError(date, messageManager.getMessage("message.date.more.current"));
            }
        }
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        try {

            int currentUserId = getCurrentUserId();
            if(!StringUtils.isEmpty(date)) {
                userInfo.setDateOfBirth(Date.valueOf(date));
            }
            UserLogic.updateUserInfo(currentUserId, userInfo);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            result = ERROR;
            exception = new CommandException(e.getCause());
        }
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public UserInfo getModel() {
        return userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getMessageDateIncorrect() {
        return messageDateIncorrect;
    }

    public void setMessageDateIncorrect(String messageDateIncorrect) {
        this.messageDateIncorrect = messageDateIncorrect;
    }

    public String getMessageNameEmpty() {
        return messageNameEmpty;
    }

    public void setMessageNameEmpty(String messageNameEmpty) {
        this.messageNameEmpty = messageNameEmpty;
    }
}