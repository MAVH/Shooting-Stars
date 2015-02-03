package com.shooting_stars.project.manager;

import java.util.ResourceBundle;

public class ConfigManager {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.file_path");

    private ConfigManager() {}

    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
