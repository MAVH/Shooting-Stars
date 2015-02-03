package com.shooting_stars.project.command;

import com.shooting_stars.project.controller.Controller;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.UserToBeRegistered;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.RegistrationException;
import com.shooting_stars.project.logic.RegistrationLogic;
import com.shooting_stars.project.manager.ConfigManager;
import com.shooting_stars.project.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class PreparingRegistrationCommand implements Command {
    private final String PARAM_PART = "part";
    private final String PARAM_LOGIN = "login";
    private final String PARAM_PASSWORD = "password";
    private final String PARAM_PASSWORD_REPEAT = "password_repeat";
    private final String PARAM_EMAIL = "e-mail";
    private final String PARAM_NAME = "name";
    private final String PARAM_SURNAME = "surname";
    private final String PARAM_COUNTRY = "country";
    private final String PARAM_CITY = "city";
    private final String PARAM_DATE = "dateOfBirth";
    private final String PARAM_SUBMIT = "submitAction";
    private final String PARAM_ABILITIES = "abilities";
    private final String REG_PAGE1 = ConfigManager.getProperty("path.page.registration1");
    private final String REG_PAGE2 = ConfigManager.getProperty("path.page.registration2");
    private final String REG_PAGE3 = ConfigManager.getProperty("path.page.registration3");

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String partValue = request.getParameter(PARAM_PART);
        UserToBeRegistered user;
        int part = Integer.parseInt(partValue);
        HttpSession session = request.getSession();
        if(session.getAttribute("user_registry") == null) {
            user = new UserToBeRegistered();
            session.setAttribute("user_registry", user);
        } else {
            user = (UserToBeRegistered) session.getAttribute("user_registry");
        }
        boolean forward = false;
        String submitValue = request.getParameter(PARAM_SUBMIT);
        if(!Validation.isEmpty(submitValue)) {
            if (submitValue.equals(ResourceBundle.getBundle("resources.pagecontent", (Locale) session.getAttribute("locale")).getString("continue"))) {
                forward = true;
            }
        }
        boolean emptyFields = false;
        String page;
        switch (part) {
            case 1:
                String login = request.getParameter(PARAM_LOGIN);
                String password = request.getParameter(PARAM_PASSWORD);
                String password_repeat = request.getParameter(PARAM_PASSWORD_REPEAT);
                String email = request.getParameter(PARAM_EMAIL);
                boolean differentPasswords = false;
                user.setEmail(email);
                //TODO: what if the same login as already exists? need to check this
                user.setLogin(login);
                if(password.equals(password_repeat)) {
                    user.setPassword(password);
                } else {
                    differentPasswords = true;
                }
                if(Validation.isEmpty(login) || Validation.isEmpty(password) || Validation.isEmpty(password_repeat)) {
                    emptyFields = true;
                }
                if(emptyFields) {
                    request.setAttribute("registrationError", Controller.messageManager.getMessage("message.fields.empty"));
                    page = REG_PAGE1;
                } else if(differentPasswords) {
                    request.setAttribute("registrationError", Controller.messageManager.getMessage("message.passwords.unequal"));
                    page = REG_PAGE1;
                } else {
                    page = REG_PAGE2;
                }
                return page;
            case 2:
                String name = request.getParameter(PARAM_NAME);
                String surname = request.getParameter(PARAM_SURNAME);
                String country = request.getParameter(PARAM_COUNTRY);
                String city = request.getParameter(PARAM_CITY);
                String dateOfBirth = request.getParameter(PARAM_DATE);
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
                    page = REG_PAGE1;
                } else if(Validation.isEmpty(name)) {
                    request.setAttribute("registrationError", Controller.messageManager.getMessage("message.fields.empty"));
                    page = REG_PAGE2;
                } else {
                    page = REG_PAGE3;
                }
                return page;
            case 3:
                String abilities = request.getParameter(PARAM_ABILITIES);
                user.setAbilities(abilities);
                if(!forward) {
                    page = REG_PAGE2;
                } else {
                    try {
                        User new_user = RegistrationLogic.addUser(user);
                        if (new_user != null) {
                            session.setAttribute("user", user);
                            session.removeAttribute("user_registry");
                            page = ConfigManager.getProperty("path.page.main");
                        } else {
                            request.setAttribute("registrationError", Controller.messageManager.getMessage("message.registration.error"));
                            page = REG_PAGE3;
                        }
                    } catch (RegistrationException e) {
                        throw new CommandException(e.getCause());
                    }
                }
                return page;
        }
        return null;
    }
}
