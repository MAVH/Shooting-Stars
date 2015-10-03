package com.shooting_stars.project.listener;

import com.shooting_stars.project.manager.MessageManager;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

@WebListener
public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se){
        se.getSession().setAttribute("currentLocale", Locale.getDefault());
        se.getSession().setAttribute("messageManager",MessageManager.INSTANCE);
    }

    public void sessionDestroyed(HttpSessionEvent se){
         se.getSession().removeAttribute("currentUserId");
    }
}
