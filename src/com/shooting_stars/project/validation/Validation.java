package com.shooting_stars.project.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile("\\w{6,}");
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.matches();
        return result;

        //Pattern pattern = Pattern.compile("/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,}$/");
    }
}
