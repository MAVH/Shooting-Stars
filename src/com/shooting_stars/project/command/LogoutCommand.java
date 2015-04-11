package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request = null;
    @Override
    public String execute() {
       request.getSession().removeAttribute("user");
       return SUCCESS;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
          this.request = request;
    }
}
