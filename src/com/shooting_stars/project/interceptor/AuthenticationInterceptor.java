package com.shooting_stars.project.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;


public class AuthenticationInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();
        if(session.get( "currentUserId") == null) {
            return Action.LOGIN;
        }
        else
        {
            Action action = ( Action ) actionInvocation.getAction ( );
            return actionInvocation.invoke ( );
        }
    }
}
