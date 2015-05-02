package com.shooting_stars.project.command;


import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import com.shooting_stars.project.manager.MessageManager;
import org.apache.commons.lang3.StringUtils;

public class ChangeEmailCommand extends SessionAwareCommand {
    private String email;
    private MessageManager messageManager;
    private String operationInfo;
    @Override
    public void validate() {
        messageManager = (MessageManager)sessionAttributes.get("messageManager");
        if(StringUtils.isEmpty(email)) {
            addFieldError("email", messageManager.getMessage("message.email.empty"));
        }
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        int currentUserId = getCurrentUserId();
        try {
             UserLogic.changeEmail(currentUserId,email);
             operationInfo = messageManager.getMessage("message.email.changed");
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessageOperationInfo() {
        return operationInfo;
    }

}
