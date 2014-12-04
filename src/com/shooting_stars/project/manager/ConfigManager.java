package com.shooting_stars.project.manager;

import java.util.ResourceBundle;

/**
 * Created by Пользователь on 07.10.2014.
 */
public class ConfigManager {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.config");
    private ConfigManager() {}
    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
