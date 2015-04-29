package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GetUsersWishesFulfilledCommand extends SessionAwareCommand {
    private int userId;
    private ArrayList<Wish> wishes;
    private String[] dateValues;

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


    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            wishes = WishLogic.getFulfilledWishesByOwnerId(userId);
            dateValues =new String[wishes.size()];
            Locale locale = (Locale)sessionAttributes.get("currentLocale");
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
            int i = 0;
            for (Wish wish : wishes) {
                if(wish.getDate() != null) {
                    dateValues[i] = dateFormat.format(wish.getDate());
                }
                i++;
            }

        } catch (LogicException e) {
            LOG.error(e.getMessage(), e.getCause());
            exception =  new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public String[] getDateValues() {
        return dateValues;
    }

}
