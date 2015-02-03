package com.shooting_stars.project.validation;

public class Validation {
    public static boolean isEmpty(String string) {
        boolean result = false;
        if(string == null) {
            result = true;
        } else if(string.isEmpty()) {
            result = true;
        }
        return result;
    }
}
