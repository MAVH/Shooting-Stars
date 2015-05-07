package com.shooting_stars.project.tags;


import com.shooting_stars.project.logic.MessageLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.shooting_stars.project.logic.SearchLogic;
import org.apache.commons.lang3.StringUtils;

public class WishesPagerTag extends TagSupport {
    private int generalAmount;
    private int currentPage;
    private String wish;
    private static final int PAGER_AMOUNT = 5;
    private static final String SEARCH_QUERY = "wishesSearch?wish=";
    private static final String RECENT_WISHES_QUERY = "recentWishes?";
    @Override
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            HttpSession session = request.getSession();
            ResourceBundle rs = ResourceBundle.getBundle("resources.pagecontent", (Locale) session.getAttribute("currentLocale"));
            JspWriter out = pageContext.getOut();
            int generalAmountOfPages = (int) Math.ceil((double)generalAmount / SearchLogic.WISHES_AMOUNT_ON_ONE_PAGE);
            int pageNumber;
            int firstPageNumber;
            int pagerHalf = (int) Math.ceil((double) PAGER_AMOUNT / 2);
            String query;
            if(StringUtils.isEmpty(wish)) {
                query = RECENT_WISHES_QUERY;
            } else {
                query = SEARCH_QUERY + wish + "&";
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

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
