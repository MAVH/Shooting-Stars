package com.shooting_stars.project.command;


import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.SearchLogic;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UsersSearchCommand extends SessionAwareCommand {
    private String name;
    private String surname;
    private String country;
    private String city;
    private String dateOfBirthMin;
    private String dateOfBirthMax;
    private List<User> foundUsers;
    private int page = 1;
    private int usersAmount;

    @Override
    public void validate() {
        if(StringUtils.isEmpty(name) && StringUtils.isEmpty(surname)) {
            addFieldError("name", "Error" );
        }
    }
    @Override
    public String execute() {
        String result = SUCCESS;
        try {
            foundUsers = SearchLogic.findUsers(name, surname, city, country, dateOfBirthMin, dateOfBirthMax, page);
            usersAmount = SearchLogic.countUsers(name, surname, city, country, dateOfBirthMin, dateOfBirthMax);
        } catch (LogicException e) {
            LOG.error(e.getMessage(),e.getCause());
            exception = new CommandException(e.getCause());
            result = ERROR;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateOfBirthMin() {
        return dateOfBirthMin;
    }

    public void setDateOfBirthMin(String dateOfBirthMin) {
        this.dateOfBirthMin = dateOfBirthMin;
    }

    public String getDateOfBirthMax() {
        return dateOfBirthMax;
    }

    public void setDateOfBirthMax(String dateOfBirthMax) {
        this.dateOfBirthMax = dateOfBirthMax;
    }

    public List<User> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<User> foundUsers) {
        this.foundUsers = foundUsers;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getUsersAmount() {
        return usersAmount;
    }

    public void setUsersAmount(int usersAmount) {
        this.usersAmount = usersAmount;
    }
}
