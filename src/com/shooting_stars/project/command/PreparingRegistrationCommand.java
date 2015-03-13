package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserToBeRegistered;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.RegistrationException;
import com.shooting_stars.project.logic.RegistrationLogic;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.validation.Validation;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.InputStream;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class PreparingRegistrationCommand extends ActionSupport {
    private String partValue;
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
    /*
    private final String REG_PAGE1 = ConfigManager.getProperty("path.page.registration1");
    private final String REG_PAGE2 = ConfigManager.getProperty("path.page.registration2");
    private final String REG_PAGE3 = ConfigManager.getProperty("path.page.registration3");     */
    private static final String STEP1 = "step1";
    private static final String STEP2 = "step2";
    private static final String STEP3 = "step3";
    private String registrationError;
    private String registrationLoginError;
    private String registrationPasswordError;
    private Exception exception;

    public String getPartValue() {
        return partValue;
    }

    public void setPartValue(String partValue) {
        this.partValue = partValue;
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
        UserToBeRegistered user;
        int part = Integer.parseInt(partValue);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        if(session.getAttribute("user_registry") == null) {
            user = new UserToBeRegistered();
            session.setAttribute("user_registry", user);
        } else {
            user = (UserToBeRegistered) session.getAttribute("user_registry");
        }
        boolean forward = false;
        if(!Validation.isEmpty(submitAction)) {
            if (submitAction.equals(ResourceBundle.getBundle("resources.pagecontent", (Locale) session.getAttribute("currentLocale")).getString("continue"))) {
                forward = true;
            }
        }
        boolean emptyFields = false;
        String page;
        String result;
        switch (part) {
            case 1:
                boolean error = false;
                user.setEmail(email);
                //TODO: what if the same login as already exists? need to check this
                try {
                    if(RegistrationLogic.userLoginExists(login)) {
                        user.setLogin("");
                        error = true;
                        registrationLoginError = Controller.messageManager.getMessage("message.login.exists");
                    } else {
                        user.setLogin(login);
                    }
                } catch (RegistrationException e) {
                    throw new CommandException(e.getCause());
                }
                if(password.equals(password_repeat)) {
                    user.setPassword(password);
                } else {
                    user.setPassword("");
                    registrationPasswordError = Controller.messageManager.getMessage("message.passwords.unequal");
                    error = true;
                }
                if(Validation.isEmpty(login) || Validation.isEmpty(password) || Validation.isEmpty(password_repeat)) {
                    registrationError = Controller.messageManager.getMessage("message.fields.empty");
                    error = true;
                }
                if(error) {
                   // page = REG_PAGE1;
                    result = STEP1;
                } else {
                    //page = REG_PAGE2;
                    result = STEP2;
                }
               // return page;
                return result;
            case 2:
                user.setName(name);
                user.setSurname(surname);
                user.setCountry(country);
                user.setCity(city);
                if(!Validation.isEmpty(dateOfBirth)) {
                    user.setDateOfBirth(Date.valueOf(dateOfBirth));
                } else {
                    user.setDateOfBirth(null);
                }
                if(!forward) {
                    result = STEP1;
                } else if(Validation.isEmpty(name)) {
                    registrationError = Controller.messageManager.getMessage("message.fields.empty");
                    result = STEP2;
                } else {
                    result = STEP3;
                }
                return result;
            case 3:
                user.setAbilities(abilities);
                if(!forward) {
                    result = STEP2;
                } else {
                    try {
                        User new_user = RegistrationLogic.addUser(user);
                        if (new_user != null) {
                            session.setAttribute("user", new_user);
                            session.removeAttribute("user_registry");
                            result = SUCCESS;
                            //page = ConfigManager.getProperty("path.page.main");
                        } else {
                            registrationError = Controller.messageManager.getMessage("message.registration.error");
                            result = STEP3;
                        }
                    } catch (RegistrationException e) {
                        exception =  new CommandException(e.getCause());
                        result = ERROR;
                    }
                }
                return result;
        }
        return null;
    }
}
