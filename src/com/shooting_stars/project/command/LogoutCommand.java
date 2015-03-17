package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.manager.ConfigManager;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LogoutCommand extends ActionSupport implements ServletRequestAware {
    private HttpServletRequest request = null;
    @Override
    public String execute() {
       request.getSession().invalidate();
       return SUCCESS;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
          this.request = request;
    }
}
