package com.shooting_stars.project.command;


import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class SessionAwareCommand extends Command implements SessionAware {
    protected Map<String, Object> sessionAttributes = null;
    private static final String PARAM_CURRENT_USER_ID = "currentUserId";
    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionAttributes = stringObjectMap;
    }
    protected int getCurrentUserId() {
        return (Integer)sessionAttributes.get(PARAM_CURRENT_USER_ID);
    }
}
