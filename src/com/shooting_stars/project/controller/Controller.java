package com.shooting_stars.project.controller;

import com.shooting_stars.project.command.ActionFactory;
import com.shooting_stars.project.command.Command;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.manager.MessageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Пользователь on 14.11.2014.
 */
@WebServlet ("/controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        Command command = ActionFactory.defineCommand(request);
        page = command.execute(request);
        if(page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request,response);
        } else {
            request.getSession().setAttribute("nullPage", MessageManager.getMessage("message.nullpage", (Locale) request.getSession().getAttribute("locale")));
            response.sendRedirect(request.getContextPath() + ConfigManager.getProperty("path.page.index"));
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = this.getServletContext().getRealPath("/");
        new DOMConfigurator().doConfigure(path + "/log4j.xml", LogManager.getLoggerRepository());
    }
}
