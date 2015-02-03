package com.shooting_stars.project.manager;

import java.util.Locale;

public enum LocaleManager {
    EN(new Locale("en","US")),
    RU(new Locale("ru", "RU"));

    private Locale locale;

    LocaleManager(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
