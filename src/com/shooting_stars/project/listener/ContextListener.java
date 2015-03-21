package com.shooting_stars.project.listener;

import com.shooting_stars.project.pool.Pool;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String path = servletContextEvent.getServletContext().getRealPath("/");
        new DOMConfigurator().doConfigure(path + "/log4j.xml", LogManager.getLoggerRepository());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Pool.getPool().closePool();
    }
}
