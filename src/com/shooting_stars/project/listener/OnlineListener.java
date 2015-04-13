package com.shooting_stars.project.listener;

import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class OnlineListener implements HttpSessionAttributeListener {

    Logger LOG = Logger.getLogger(OnlineListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        changeStatus(httpSessionBindingEvent, 1);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        changeStatus(httpSessionBindingEvent, 0);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    private void changeStatus(HttpSessionBindingEvent httpSessionBindingEvent, int newStatusId) {
        if(httpSessionBindingEvent.getName().equals("currentUserId")) {
            int id = (Integer)httpSessionBindingEvent.getValue();
            try {
                UserLogic.setUserStatus(id, newStatusId);
            } catch(LogicException e) {
                LOG.error(e.getMessage(), e.getCause());
            }
        }
    }
}
