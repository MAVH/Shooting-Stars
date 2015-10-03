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

public class AddWishBlockTag extends TagSupport {
    private int count;
    private static final int MAX_SIZE = 5;
    @Override
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            HttpSession session = request.getSession();
            ResourceBundle rs = ResourceBundle.getBundle("resources.pagecontent", (Locale)session.getAttribute("currentLocale"));
            JspWriter out = pageContext.getOut();
            if (count != MAX_SIZE) {
                int number = MAX_SIZE - count;
                String submitValue = rs.getString("save");
                String buttonValue = rs.getString("wish_add_button");
                String label = rs.getString("wish_input");
                out.write("<form action=/saveWishes method=post class='addWishesBlock hidden' id=add_wish_form><legend>" + label + "</legend>");
                //for (int i = 0; i < number; i++) {
                out.write("<input type=text name=wish class='form-control hidden'>");
                //}
                out.write("<input type=submit id=save_wish_button value=" + submitValue + ">");
                out.write("</form>");
                out.write("<button id=button_add_wish class=hidden>" + buttonValue + "</button>");
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage(),e);
        }
        return SKIP_BODY;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

