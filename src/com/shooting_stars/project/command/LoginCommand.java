package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.LoginLogic;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.manager.MessageManager;
import com.shooting_stars.project.validation.Validation;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand extends ActionSupport {
    private String login;
    private String password;
    private String loginOrPasswordErrorMessage;
    private Exception exception;

    @Override
    public String execute() {
        String result = null;
        if(Validation.isEmpty(login) || Validation.isEmpty(password)) {
            loginOrPasswordErrorMessage = Controller.messageManager.getMessage("message.fields.empty");
            result = LOGIN;
        }  else {
            try {
                User user = LoginLogic.checkUser(login, password);
                if (user != null) {
                    ServletActionContext.getRequest().getSession().setAttribute("user", user);
                    result = SUCCESS;
                } else {
                    loginOrPasswordErrorMessage = Controller.messageManager.getMessage("message.login.error");
                    result = LOGIN;
                }
            } catch (LogicException e) {
                result = ERROR;
                exception = new CommandException(e.getCause());
            }
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

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
