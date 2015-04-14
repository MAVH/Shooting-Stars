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


public class MessagesPagerTag extends TagSupport {
    private int generalAmount;
    private int chatId;
    private int currentPage;
    private static final int PAGER_AMOUNT = 5;
    @Override
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            HttpSession session = request.getSession();
            ResourceBundle rs = ResourceBundle.getBundle("resources.pagecontent", (Locale)session.getAttribute("currentLocale"));
            JspWriter out = pageContext.getOut();
            int pageNumber;
            if(generalAmount > MessageLogic.MESSAGES_AMOUNT_ON_ONE_PAGE) {
                if (currentPage > 1) {
                    out.write("<a href=/getMessages?chatId=" + chatId + "&page=" + (currentPage - 1) + ">" +
                            rs.getString("previous_page") + " </a>");
                }
                for (int i = 0; i < PAGER_AMOUNT; i++) {
                    pageNumber = currentPage + i;
                    if (pageNumber <= generalAmount / MessageLogic.MESSAGES_AMOUNT_ON_ONE_PAGE + 1) {
                        if (generalAmount % MessageLogic.MESSAGES_AMOUNT_ON_ONE_PAGE != 0) {
                            out.write("<a href=/getMessages?chatId=" + chatId + "&page=" + (pageNumber) + ">" + pageNumber + " </a>");
                        }
                    }
                }
                if (currentPage <= generalAmount / MessageLogic.MESSAGES_AMOUNT_ON_ONE_PAGE) {
                    if (generalAmount % MessageLogic.MESSAGES_AMOUNT_ON_ONE_PAGE != 0) {
                        out.write("<a href=/getMessages?chatId=" + chatId + "&page=" + (currentPage + 1) + ">" +
                                rs.getString("next_page") + " </a>");
                    }
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

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
