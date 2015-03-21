package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.LoginLogic;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LoginCommand extends ActionSupport implements SessionAware {
    static Logger logger = Logger.getLogger(LoginCommand.class);
    private String login;
    private String password;
    private String loginOrPasswordErrorMessage;
    private Exception exception;
    private final static String LOGIN_FAILED = "login_failed";

    private Map<String, Object> sessionAttributes = null;


    @Override
    public void validate() {
        if(StringUtils.isEmpty(login)) {
            addFieldError("login",Controller.messageManager.getMessage("message.fields.empty"));
        }
        if( StringUtils.isEmpty(password)) {
            addFieldError("password",Controller.messageManager.getMessage("message.fields.empty"));
        }
    }
    @Override
    public String execute() {
        String result = null;
        /*
        if(Validation.isEmpty(login) || Validation.isEmpty(password)) {
            loginOrPasswordErrorMessage = Controller.messageManager.getMessage("message.fields.empty");
            result  LOGIN;
        }  else { */
            try {
                User user = LoginLogic.checkUser(login, password);
                if (user != null) {
                    sessionAttributes.put("user", user);
                    result = SUCCESS;
                } else {
                    loginOrPasswordErrorMessage = Controller.messageManager.getMessage("message.login.error");
                    result = LOGIN_FAILED;
                }
            } catch (LogicException e) {
                logger.error(e.getMessage(), e.getCause());
                result = ERROR;
                exception = new CommandException(e.getCause());
            }
        /*}   */
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

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.sessionAttributes = stringObjectMap;
    }
}
