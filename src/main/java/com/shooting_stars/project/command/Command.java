package com.shooting_stars.project.command;


import com.opensymphony.xwork2.ActionSupport;

public class Command extends ActionSupport {
    protected Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
