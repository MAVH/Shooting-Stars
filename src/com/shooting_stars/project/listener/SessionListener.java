package com.shooting_stars.project.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;
@WebListener
public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se){
        se.getSession().setAttribute("locale", Locale.getDefault());
    }

    public void sessionDestroyed(HttpSessionEvent se){
          se.getSession().removeAttribute("user");
    }
}
