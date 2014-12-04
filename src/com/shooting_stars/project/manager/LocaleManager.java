package com.shooting_stars.project.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Пользователь on 07.10.2014.
 */
public enum LocaleManager {
    EN(new Locale("en","EN")),
    RU(new Locale("ru", "RU"));
    private Locale locale;
    LocaleManager(Locale locale) {
        this.locale = locale;
    }
    public Locale getLocale() {
        return locale;
    }
}
