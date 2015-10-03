package com.shooting_stars.project.command;

import com.shooting_stars.project.entity.UserInfo;
import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import com.shooting_stars.project.logic.WishLogic;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class FormUserPageCommand extends SessionAwareCommand implements ServletRequestAware {

    private HttpServletRequest request = null;
    private int userId;
    private ArrayList<Wish> wishes;
    private String status;
    private static final String USER = "user";
    private static final String OTHER_USER = "other_user";
    private UserInfo userInfo;
    private int wishesCount;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(ArrayList<Wish> wishes) {
        this.wishes = wishes;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String execute() {
        String result = OTHER_USER;
        try {
            int currentUserId = getCurrentUserId();
            if(request.getParameter("userId") == null) {
                    userId = currentUserId;
            }
            if (userId == currentUserId) {
                result = USER;
                wishesCount = WishLogic.getCurrentWishesAmount(userId);
                UserLogic.updateVisitTime(currentUserId);
            }
            userInfo = UserLogic.getUserInfo(userId);
            //wishes = WishLogic.getAllWishes(userId);

            status = UserLogic.getUserStatus(userId);

        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
          this.request = request;
    }

    public int getWishesCount() {
        return wishesCount;
    }

    public void setWishesCount(int wishesCount) {
        this.wishesCount = wishesCount;
    }
}
