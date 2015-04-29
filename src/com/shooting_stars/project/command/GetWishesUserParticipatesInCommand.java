package com.shooting_stars.project.command;

import com.shooting_stars.project.entity.Wish;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.WishLogic;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GetWishesUserParticipatesInCommand extends SessionAwareCommand {
    private int userId;
    private ArrayList<Wish> wishes;
    String[] dateValues;
    private boolean isPageOwner;

    public boolean getIsPageOwner() {
        return isPageOwner;
    }

    public void setPageOwner(boolean isOwner) {
        this.isPageOwner = isOwner;
    }

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
            int currentUserId = getCurrentUserId();
            if(currentUserId == userId) {
                isPageOwner = true;
            } else {
                isPageOwner = false;
            }
            wishes = WishLogic.getWishesByMakerId(userId);
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

    public void setDateValues(String[] dateValues) {
        this.dateValues = dateValues;
    }
}
