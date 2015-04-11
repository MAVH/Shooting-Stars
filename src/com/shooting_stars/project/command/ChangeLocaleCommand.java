package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.manager.LocaleManager;
import com.shooting_stars.project.manager.MessageManager;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Locale;
import java.util.Map;

public class ChangeLocaleCommand extends ActionSupport implements SessionAware {
    private static final String PARAM_MESSAGE_MANAGER = "messageManager";
    private String localeValue;
    private Map<String, Object> sessionAttributes = null;
    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }
    @Override
    public String execute() {
        String newLocale = localeValue;
        Locale locale = LocaleManager.valueOf(newLocale).getLocale();
        MessageManager messageManager = (MessageManager)sessionAttributes.get(PARAM_MESSAGE_MANAGER);
        messageManager.changeLocale(locale);
        sessionAttributes.put("currentLocale", locale);
        //messageManager.getMessage("message.login.error");
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
