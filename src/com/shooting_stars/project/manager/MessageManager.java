package com.shooting_stars.project.manager;


import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
        INSTANCE;
        private ResourceBundle resourceBundle;
        private final String resourceName = "resources.message";
        private MessageManager() {
            resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
        }
        public void changeLocale(Locale locale) {
            resourceBundle = ResourceBundle.getBundle(resourceName, locale);
        }
        public String getMessage(String key) {
            return resourceBundle.getString(key);
        }
}
