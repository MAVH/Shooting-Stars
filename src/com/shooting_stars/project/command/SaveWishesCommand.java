package com.shooting_stars.project.command;

import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class SaveWishesCommand extends SessionAwareCommand implements ServletRequestAware {
    private HttpServletRequest request = null;
    private static final String PARAM_WISH = "wish";
    @Override
    public String execute() {
        String result = SUCCESS;
        String[] wishes = request.getParameterValues(PARAM_WISH);
        int userId = getCurrentUserId();
        try {
            WishLogic.addWishes(userId,wishes);
        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }
}
