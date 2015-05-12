package com.shooting_stars.project.command;

import com.shooting_stars.project.manager.LocaleManager;
import com.shooting_stars.project.manager.MessageManager;

import java.util.Locale;

public class ChangeLocaleCommand extends SessionAwareCommand {
    private static final String PARAM_MESSAGE_MANAGER = "messageManager";
    private String localeValue;

    @Override
    public String execute() {
        String newLocale = localeValue;
        Locale locale = LocaleManager.valueOf(newLocale).getLocale();
        MessageManager messageManager = (MessageManager)sessionAttributes.get(PARAM_MESSAGE_MANAGER);
        messageManager.changeLocale(locale);
        sessionAttributes.put("currentLocale", locale);
        //messageManager.getMessage("message.login.error");
        //TODO: return current page
        return SUCCESS;

    }

    public String getLocaleValue() {
        return localeValue;
    }

    public void setLocaleValue(String localeValue) {
        this.localeValue = localeValue;
    }
}
