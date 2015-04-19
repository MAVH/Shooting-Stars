package com.shooting_stars.project.tags;

import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.entity.Wish;
import org.apache.commons.lang3.StringUtils;

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

@SuppressWarnings("serial")
public class WishListTag extends TagSupport {
    private static final int MAX_SIZE = 5;
    private ArrayList<Wish> list;
    private boolean isProfilePage;


    public void setList(ArrayList<Wish> list) {
        this.list = list;
    }

    public boolean isProfilePage() {
        return isProfilePage;
    }

    public void setIsProfilePage(boolean isProfilePage) {
        this.isProfilePage = isProfilePage;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            //isProfilePage = true;
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            HttpSession session = request.getSession();
            //User currentUser = (User)session.getAttribute("user");
            int currentUserId = (Integer)session.getAttribute("currentUserId");
            /*
            if(request.getParameter("userId") != null) {
                if(currentUser.getUserId() != Integer.parseInt(request.getParameter("userId"))) {
                    isProfilePage = false;
                }
            }  */
            int size = list.size();
            ResourceBundle rs = ResourceBundle.getBundle("resources.pagecontent", (Locale)session.getAttribute("currentLocale"));
            JspWriter out = pageContext.getOut();
            if(size != 0) {
                String buttonDelete = rs.getString("delete");
                out.write("<table id=wish_table class=table><tr><th></th><th>" + rs.getString("wishes") + "</th><th></th></tr>");
                String candidatesList = "";
                User candidate = null;
                String label = null;
                String formAction;
                String photoName;
                String buttonTake = rs.getString("take_application");
                String buttonCancel = rs.getString("cancel");
                for(Wish wish:list) {
                    candidatesList = "";
                    if ((candidate = wish.getCandidate()) != null) {
                        label = rs.getString("wish_performed");
                        candidatesList = "<h5>" + label + "</h5><table><tr><td><a href=userPage?userId=" +
                                candidate.getUserId() + ">";
                        photoName = candidate.getPhotoName();
                        if(StringUtils.isEmpty(photoName)) {
                            photoName = UserPhotoTag.DEFAULT_IMAGE_NAME;
                        }
                        candidatesList += "<img src=" + pageContext.getServletContext().getContextPath() + UserPhotoTag.PATH + photoName +
                                " class=iconPhoto />"
                                + candidate.getName() + " " + candidate.getSurname() + "</a></td>";
                        if(isProfilePage) {
                            candidatesList += "<td><form action=cancelMakingWish method=post><input type=hidden name=wishId value=" + wish.getWishId()
                                    + "><input type=submit value="
                                    + buttonCancel + "></form></td>";
                            candidatesList += "<td><form action=wishMade method=post><input type=hidden name=wishId value=" + wish.getWishId()
                                    + "><input type=submit value="
                                    + "isDone" + "></form></td>";
                        }
                        candidatesList += "</tr></table>";
                    }
                    if(wish.getCandidates() != null) {
                        if(!wish.getCandidates().isEmpty()) {
                            label = rs.getString("applications");
                            candidatesList = "<h5>" + label + "</h5>";
                            for (Object user : wish) {

                                candidatesList += "<a href=userPage?userId=" + ((User) user).getUserId() + ">";
                                photoName = ((User) user).getPhotoName();
                                if(StringUtils.isEmpty(photoName)) {
                                    photoName = UserPhotoTag.DEFAULT_IMAGE_NAME;
                                }
                                candidatesList += "<img src=" + pageContext.getServletContext().getContextPath() + UserPhotoTag.PATH + photoName +
                                        " class=iconPhoto />";
                                candidatesList += ((User) user).getName()
                                         + " " + ((User) user).getSurname()+ "</a>";
                                if(isProfilePage) {
                                    candidatesList += "<form action=acceptApplication method=post><input type=hidden name=wishId value=" + wish.getWishId()
                                            + "><input type=hidden name=userId value=" + ((User) user).getUserId() + "><input type=submit value="
                                            + buttonTake + "></form>";
                                    candidatesList += "<form action=cancelApplication method=post><input type=hidden name=wishId value=" + wish.getWishId()
                                            + "><input type=hidden name=userId value=" + ((User) user).getUserId() + "><input type=submit value="
                                            + buttonCancel + "></form>";
                                }
                            }
                        }
                    }
                    formAction = "";
                    if (isProfilePage) {
                        formAction = "<form action=deleteWish method=post><input type=hidden name=wishId value="
                                + wish.getWishId() + "><input type=submit value=" + buttonDelete + "></form>";
                    } else {
                        if(wish.getCandidates().contains(new User(currentUserId))) {
                            formAction = "<form action=cancelApplication method=post><input type=hidden name=pageCode value=0> <input type=hidden name=wishId value=" + wish.getWishId()
                                    + "><input type=hidden name=userId value=" + currentUserId + "><input type=submit value="
                                    + "Cancel my application" + "></form>";
                        } else {
                            if(candidate == null) {
                                formAction = "<form action=makeApplication method=post><input type=hidden name=wishId value=" + wish.getWishId()
                                        + "><input type=submit value="
                                        + "make application" + "></form>";
                            }
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
                    //for (int i = 0; i < number; i++) {
                        out.write("<input type=text name=wish class=hidden>");
                    //}
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

