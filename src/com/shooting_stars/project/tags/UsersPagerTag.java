package com.shooting_stars.project.tags;

import com.shooting_stars.project.logic.SearchLogic;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class UsersPagerTag extends TagSupport {
    private int generalAmount;
    private int currentPage;
    private String name;
    private String surname;
    private String country;
    private String city;
    private String dateOfBirthMin;
    private String dateOfBirthMax;
    private static final int PAGER_AMOUNT = 5;
    private static final String SEARCH_QUERY = "usersSearch?name=";
    private static final String BIRTHDAY_MEN_QUERY = "birthdayMen?";
    @Override
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            HttpSession session = request.getSession();
            ResourceBundle rs = ResourceBundle.getBundle("resources.pagecontent", (Locale) session.getAttribute("currentLocale"));
            JspWriter out = pageContext.getOut();
            int generalAmountOfPages = (int) Math.ceil((double)generalAmount / SearchLogic.USERS_AMOUNT_ON_ONE_PAGE);
            int pageNumber;
            int firstPageNumber;
            int pagerHalf = (int) Math.ceil((double) PAGER_AMOUNT / 2);
            String query;
            if(StringUtils.isEmpty(name) && StringUtils.isEmpty(surname)) {
                query = BIRTHDAY_MEN_QUERY;
            } else {
                query = SEARCH_QUERY + name + "&surname=" + surname + "&country=" + country + "&city=" + city
                + "&dateOfBirthMin=" + dateOfBirthMin + "&dateOfBirthMax=" + dateOfBirthMax + "&" ;
            }
            if(generalAmountOfPages > 1) {
                if (currentPage > 1) {
                    out.write("<a href=/" + query + "page=" + 1 + ">" +
                            rs.getString("first_page") + " </a>");
                    out.write("<a href=/" + query + "page=" + (currentPage - 1) + ">" +
                            rs.getString("previous_page") + " </a>");
                }
                if(currentPage <= pagerHalf || generalAmountOfPages <= PAGER_AMOUNT) {
                    firstPageNumber = 1;
                } else if(generalAmountOfPages - currentPage < pagerHalf) {
                    firstPageNumber = generalAmountOfPages - PAGER_AMOUNT + 1;
                } else {
                    firstPageNumber = currentPage - pagerHalf + 1;
                }
                for (int i = 0; i < PAGER_AMOUNT; i++) {
                    pageNumber = firstPageNumber + i;
                    if(pageNumber == currentPage) {
                        out.write("<a class = 'currentPage' href=/" + query + "page=" + (pageNumber) + ">" + pageNumber + " </a>");
                    } else if (pageNumber <= generalAmountOfPages) {
                        out.write("<a href=/" + query + "page=" + (pageNumber) + ">" + pageNumber + " </a>");
                    }
                }
                if (currentPage < generalAmountOfPages) {
                    out.write("<a href=/" + query + "page=" + (currentPage + 1) + ">" +
                            rs.getString("next_page") + " </a>");
                    out.write("<a href=/" + query + "page=" + generalAmountOfPages + ">" +
                            rs.getString("last_page") + " ( " + generalAmountOfPages + " ) </a>");
                }
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage(),e);
        }
        return SKIP_BODY;
    }


    public int getGeneralAmount() {
        return generalAmount;
    }

    public void setGeneralAmount(int generalAmount) {
        this.generalAmount = generalAmount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
}
