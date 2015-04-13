package com.shooting_stars.project.command;


import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class SessionAwareCommand extends Command implements SessionAware {
    protected Map<String, Object> sessionAttributes = null;
    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }
}
