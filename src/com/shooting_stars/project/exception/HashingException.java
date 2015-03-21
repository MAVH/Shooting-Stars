package com.shooting_stars.project.exception;

public class HashingException extends Exception {
    public HashingException(String message) {
        super(message);
    }

    public HashingException(String message, Throwable cause) {
        super(message, cause);
    }

    public HashingException(Throwable cause) {
        super(cause);
    }
}
