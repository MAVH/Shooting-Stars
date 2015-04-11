package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserToBeRegistered;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.RegistrationException;
import com.shooting_stars.project.logic.RegistrationLogic;
import com.shooting_stars.project.manager.MessageManager;
import com.shooting_stars.project.validation.Validation;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class PreparingRegistrationCommand extends ActionSupport implements ServletRequestAware,SessionAware {

    private HttpServletRequest request = null;
    private Map<String, Object> sessionAttributes = null;
    private int part;
    private  String login;
    private String password;
    private String password_repeat;
    private  String email;
    private String name;
    private  String surname;
    private String country;
    private  String city;
    private  String dateOfBirth;
    private  String submitAction;
    private  String abilities;
    private String [] wishes;
    private static final String STEP1 = "step1";
    private static final String STEP2 = "step2";
    private static final String STEP3 = "step3";
    private String registrationError;
    private String registrationLoginError;
    private String registrationPasswordError;
    private String registrationInvalidPasswordError;
    private Exception exception;

    public String getRegistrationInvalidPasswordError() {
        return registrationInvalidPasswordError;
    }

    public void setRegistrationInvalidPasswordError(String registrationInvalidPasswordError) {
        this.registrationInvalidPasswordError = registrationInvalidPasswordError;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public String[] getWishes() {
        return wishes;
    }

    public void setWishes(String[] wishes) {
        this.wishes = wishes;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_repeat() {
        return password_repeat;
    }

    public void setPassword_repeat(String password_repeat) {
        this.password_repeat = password_repeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSubmitAction() {
        return submitAction;
    }

    public void setSubmitAction(String submitAction) {
        this.submitAction = submitAction;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getRegistrationError() {
        return registrationError;
    }

    public void setRegistrationError(String registrationError) {
        this.registrationError = registrationError;
    }

    public String getRegistrationLoginError() {
        return registrationLoginError;
    }

    public void setRegistrationLoginError(String registrationLoginError) {
        this.registrationLoginError = registrationLoginError;
    }

    public String getRegistrationPasswordError() {
        return registrationPasswordError;
    }

    public void setRegistrationPasswordError(String registrationPasswordError) {
        this.registrationPasswordError = registrationPasswordError;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String execute() throws CommandException {
        MessageManager messageManager = (MessageManager)sessionAttributes.get("messageManager");
        UserToBeRegistered user;
        if(sessionAttributes.get("user_registry") == null) {
            user = new UserToBeRegistered();
            sessionAttributes.put("user_registry", user);
        } else {
            user = (UserToBeRegistered) sessionAttributes.get("user_registry");
        }
        boolean forward = false;
        if(!StringUtils.isEmpty(submitAction)) {
            if (submitAction.equals(ResourceBundle.getBundle("resources.pagecontent", (Locale) sessionAttributes.get("currentLocale")).getString("continue"))) {
                forward = true;
            }
        }
        String result;
        switch (part) {
            case 1:
                boolean error = false;
                user.setEmail(email);
                try {
                    if(RegistrationLogic.userLoginExists(login)) {
                        user.setLogin("");
                        error = true;
                        registrationLoginError = messageManager.getMessage("message.login.exists");
                    } else {
                        user.setLogin(login);
                    }
                } catch (RegistrationException e) {
                    LOG.error(e.getMessage(), e.getCause());
                    exception =  new CommandException(e.getCause());
                    return ERROR;
                }
                if(password.equals(password_repeat)) {
                    user.setPassword(password);
                } else {
                    user.setPassword("");
                    registrationPasswordError = messageManager.getMessage("message.passwords.unequal");
                    error = true;
                }
                if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password) || StringUtils.isEmpty(password_repeat)) {
                    registrationError = messageManager.getMessage("message.fields.empty");
                    error = true;
                }
                if(!Validation.checkPassword(password)) {
                    registrationInvalidPasswordError = messageManager.getMessage("message.password.invalid");
                    error = true;
                }
                if(error) {
                    result = STEP1;
                } else {
                    result = STEP2;
                }
                return result;
            case 2:
                user.setName(name);
                user.setSurname(surname);
                user.setCountry(country);
                user.setCity(city);
                if(!StringUtils.isEmpty(dateOfBirth)) {
                    user.setDateOfBirth(Date.valueOf(dateOfBirth));
                } else {
                    user.setDateOfBirth(null);
                }
                if(!forward) {
                    result = STEP1;
                } else if(StringUtils.isEmpty(name)) {
                    registrationError = messageManager.getMessage("message.fields.empty");
                    result = STEP2;
                } else {
                    result = STEP3;
                }
                return result;
            case 3:
                user.setAbilities(abilities);
                wishes = request.getParameterValues("wish");
                user.setWishes(wishes);
                if(!forward) {
                    result = STEP2;
                } else {

                    try {
                        User newUser = RegistrationLogic.addUser(user);
                        if (newUser != null) {
                            sessionAttributes.put("user", newUser);
                            sessionAttributes.remove("user_registry");
                            result = SUCCESS;
                        } else {
                            registrationError = messageManager.getMessage("message.registration.error");
                            result = STEP3;
                        }
                    } catch (RegistrationException e) {
                        LOG.error(e.getMessage(), e.getCause());
                        exception =  new CommandException(e.getCause());
                        result = ERROR;
                    }
                }
                return result;
        }
        return null;
    }
    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.sessionAttributes = stringObjectMap;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
