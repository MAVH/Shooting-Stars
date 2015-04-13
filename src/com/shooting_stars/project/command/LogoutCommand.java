package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request = null;
    private static final String PARAM_CURRENT_USER_ID = "currentUserId";
    @Override
    public String execute() {
       //request.getSession().removeAttribute("user");
       request.getSession().removeAttribute(PARAM_CURRENT_USER_ID);
       return SUCCESS;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
          this.request = request;
    }
}
