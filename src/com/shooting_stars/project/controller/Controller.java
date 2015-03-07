package com.shooting_stars.project.controller;

import com.shooting_stars.project.command.ActionFactory;
import com.shooting_stars.project.command.Command;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.manager.MessageManager;
import com.shooting_stars.project.pool.Pool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

//@WebServlet ("/controller")
public class Controller extends HttpServlet {
    static Logger logger = Logger.getLogger(Controller.class);
    public static MessageManager messageManager = MessageManager.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        Command command = ActionFactory.defineCommand(request);
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            logger.error(e.getMessage(), e.getCause());
            request.setAttribute("exception", e.getCause());
            page = ConfigManager.getProperty("path.page.error");
        }
        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("nullPage", messageManager.getMessage("message.nullpage"));
            response.sendRedirect(request.getContextPath() + ConfigManager.getProperty("path.page.index"));
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = this.getServletContext().getRealPath("/");
        new DOMConfigurator().doConfigure(path + "/log4j.xml", LogManager.getLoggerRepository());
    }

    @Override
    public void destroy() {
        super.destroy();
        Pool.getPool().closePool();
    }
}
