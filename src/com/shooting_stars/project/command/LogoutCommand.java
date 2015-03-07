package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.manager.ConfigManager;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand extends ActionSupport {
    @Override
    public String execute() {
       ServletActionContext.getRequest().getSession().invalidate();
       return SUCCESS;
    }
}
