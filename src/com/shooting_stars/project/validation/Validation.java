package com.shooting_stars.project.validation;

import java.sql.Date;
import java.time.LocalDate;
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
    public static boolean isDateBeforeCurrent(Date date) {
       return date.before(Date.valueOf(LocalDate.now()));
    }

    public static boolean isDate(String stringDate) {
        Pattern pattern = Pattern.compile("\\d{4}(-)\\d{2}(-)\\d{2}");
        Matcher matcher = pattern.matcher(stringDate);
        return matcher.matches();
    }
}
