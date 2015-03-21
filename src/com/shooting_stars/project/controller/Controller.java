package com.shooting_stars.project.controller;

import com.shooting_stars.project.manager.MessageManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet ("/controller")
public class Controller extends HttpServlet {
    static Logger logger = Logger.getLogger(Controller.class);
    public static MessageManager messageManager = MessageManager.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
