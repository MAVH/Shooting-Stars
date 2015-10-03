package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import com.shooting_stars.project.manager.MessageManager;
import com.shooting_stars.project.validation.Validation;
import org.apache.commons.lang3.StringUtils;

public class ChangePasswordCommand extends SessionAwareCommand {
    private String oldPassword;
    private String newPassword;
    private String repeatPassword;
    private String messageOperationInfo;

    MessageManager messageManager;

    @Override
    public void validate() {
        messageManager = (MessageManager)sessionAttributes.get("messageManager");
        if(StringUtils.isEmpty(oldPassword)) {
            addFieldError("oldPassword", messageManager.getMessage("message.old.password.empty"));
        }
        if(StringUtils.isEmpty(newPassword)) {
            addFieldError("newPassword", messageManager.getMessage("message.new.password.empty"));
        }
        if(StringUtils.isEmpty(repeatPassword)) {
            addFieldError("repeatPassword", messageManager.getMessage("message.repeat.password.empty"));
        }
        if(!Validation.checkPassword(newPassword)) {
            addFieldError("newPassword", messageManager.getMessage("message.password.invalid"));
        }
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        int currentUserId = getCurrentUserId();
        try {
            if(repeatPassword.equals(newPassword)) {
                if (UserLogic.passwordsEqual(currentUserId, oldPassword)) {
                    UserLogic.changePassword(currentUserId, newPassword);
                    messageOperationInfo = messageManager.getMessage("message.password.changed");
                } else {
                    messageOperationInfo = messageManager.getMessage("message.old.password.incorrect");
                }
            } else {
                messageOperationInfo = messageManager.getMessage("message.passwords.unequal");
            }
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getMessageOperationInfo() {
        return messageOperationInfo;
    }

    public void setMessageOperationInfo(String messageOperationInfo) {
        this.messageOperationInfo = messageOperationInfo;
    }
}
