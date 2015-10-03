package com.shooting_stars.project.hashing;

import com.shooting_stars.project.exception.HashingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hashing {
    public static String hashingPassword(String password) throws HashingException {
        StringBuffer hexString;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        }
        catch (NoSuchAlgorithmException e) {
            throw new HashingException("No method for hashing", e);
        }
        return hexString.toString();
    }
}
