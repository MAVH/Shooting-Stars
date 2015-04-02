package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.manager.LocaleManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;

public class ChangeLocaleCommand extends ActionSupport implements SessionAware {
    private String localeValue;
    private Map<String, Object> sessionAttributes = null;
    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }
    @Override
    public String execute() {
       // HttpServletRequest request = ServletActionContext.getRequest();
        //HttpSession session = request.getSession();
        String newLocale = localeValue;
        Locale locale = LocaleManager.valueOf(newLocale).getLocale();
        Controller.messageManager.changeLocale(locale);
        sessionAttributes.put("currentLocale", locale);
        Controller.messageManager.getMessage("message.login.error");
        //TODO: return current page
        /*
        if(sessionAttributes.get("user") != null) {
            return "user";
        } else {
            return "guest";
        }  */
        return SUCCESS;

    }

    public String getLocaleValue() {
        return localeValue;
    }

    public void setLocaleValue(String localeValue) {
        this.localeValue = localeValue;
    }

}
