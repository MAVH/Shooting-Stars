package com.shooting_stars.project.tags;

import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.Wish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Пользователь on 16.12.2014.
 */
@SuppressWarnings("serial")
public class WishListTag extends TagSupport {
    private static final int MAX_SIZE = 5;
    private ArrayList<Wish> list;
    private boolean isProfilePage = true;

    public void setList(ArrayList<Wish> list) {
        this.list = list;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            isProfilePage = true;
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            HttpSession session = request.getSession();
            User currentUser = (User)session.getAttribute("user");
            if(request.getParameter("userId") != null) {
                if(currentUser.getUserId() != Integer.parseInt(request.getParameter("userId"))) {
                    isProfilePage = false;
                }
            }
            int size = list.size();
            ResourceBundle rs = ResourceBundle.getBundle("resources.pagecontent", (Locale) pageContext.getSession().getAttribute("currentLocale"));
            JspWriter out = pageContext.getOut();
            if(size != 0) {
                String buttonDelete = rs.getString("delete");
                out.write("<table id=wish_table class=table><tr><th></th><th>" + rs.getString("wishes") + "</th><th></th></tr>");
                String candidatesList = "";
                User candidate = null;
                String label = null;
                String formAction;
                for(Wish wish:list) {
                    candidatesList = "";
                    if ((candidate = wish.getCandidate()) != null) {
                        label = rs.getString("wish_performed");
                        candidatesList = "<a href=userPage?userId=" + candidate.getUserId() + ">" + candidate.getLogin() + "</a>";
                    }
                    for (Object user : wish) {
                        candidatesList += "<a href=userPage?userId=" + ((User) user).getUserId() + ">" + ((User) user).getLogin() + "</a>";
                    }
                    if (isProfilePage) {
                        formAction = "<form action=deleteWish method=post><input type=hidden name=wishId value=" + wish.getWishId() + "><input type=submit value=" + buttonDelete + "></form>";
                    } else {
                        if(wish.getCandidates().contains(currentUser)) {
                            formAction = "cancel";
                        } else {
                            formAction = "make";
                        }
                    }
                    out.write("<tr><td>" + formAction + "</td><td>" + wish.getWish() + "</td><td>");
                   // if(candidatesList != null){

                        out.write(candidatesList);
                    //}
                    out.write("</td></tr>");


                    //  out.write("<tr><td><button><a href=deleteWish?wishId=" + wish.getWishId() + ">" + buttonDelete + "</a></button></td><td>" + wish.getWish() + "</td><td></td></tr>");
                }
                out.write("</table>");
            }
            if(isProfilePage) {
                if (size != MAX_SIZE) {
                    int number = MAX_SIZE - size;
                    String submitValue = rs.getString("save");
                    String buttonValue = rs.getString("wish_add");
                    String label = rs.getString("wish_input");
                /*
                out.write("<s:form action=saveWishes class=hidden id=add_wish_form><fieldset><legend>" + label + "</legend>");
                for (int i = 0; i < number; i++) {
                    out.write("<s:textfield key=wish class=hidden>");
                }
                out.write("<s:submit id=save_wish_button value=" + submitValue + ">");
                out.write("</fieldset></s:form>");
                 */
                    out.write("<form action=/saveWishes method=post class=hidden id=add_wish_form><fieldset><legend>" + label + "</legend>");
                    for (int i = 0; i < number; i++) {
                        out.write("<input type=text name=wish class=hidden>");
                    }
                    out.write("<input type=submit id=save_wish_button value=" + submitValue + ">");
                    out.write("</fieldset></form>");
                    out.write("<button id=button_add_wish class=btn>" + buttonValue + "</button>");
                }
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage(),e);
        }
        return SKIP_BODY;
    }
}

