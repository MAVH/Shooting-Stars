package com.shooting_stars.project.command;

import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.LoginLogic;
import com.shooting_stars.project.manager.MessageManager;
import org.apache.commons.lang3.StringUtils;

public class LoginCommand extends SessionAwareCommand {
    private String login;
    private String password;
    private String loginOrPasswordErrorMessage;
    private MessageManager messageManager;
    private final static String LOGIN_FAILED = "login_failed";
    private static final String PARAM_CURRENT_USER_ID = "currentUserId";



    @Override
    public void validate() {
        messageManager = (MessageManager)sessionAttributes.get("messageManager");
        if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
            addFieldError("login", messageManager.getMessage("message.fields.empty"));
        }
    }
    @Override
    public String execute() {
        String result;
            try {
                User user = LoginLogic.checkUser(login, password);
                if (user != null) {
                    sessionAttributes.put(PARAM_CURRENT_USER_ID,user.getUserId());
                    result = SUCCESS;
                } else {
                    loginOrPasswordErrorMessage = messageManager.getMessage("message.login.error");
                    result = LOGIN_FAILED;
                }
            } catch (LogicException e) {
                LOG.error(e.getMessage(), e.getCause());
                result = ERROR;
                exception = new CommandException(e.getCause());
            }
        return result;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginOrPasswordErrorMessage() {
        return loginOrPasswordErrorMessage;
    }

    public void setLoginOrPasswordErrorMessage(String loginOrPasswordErrorMessage) {
        this.loginOrPasswordErrorMessage = loginOrPasswordErrorMessage;
    }
}
