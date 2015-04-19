package com.shooting_stars.project.tags;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserPhotoTag extends TagSupport {

    public static final String DEFAULT_IMAGE_NAME = "default.png";
    public static final String PATH = "/img/userPhoto/";
    private String photoName;
    private String photoClass;

    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if(StringUtils.isEmpty(photoName)) {
                photoName =DEFAULT_IMAGE_NAME;
            }
            out.write("<img src=" + pageContext.getServletContext().getContextPath() + PATH + photoName +
                    " class=" + photoClass + ">");

        } catch (IOException e) {
            throw new JspTagException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoClass() {
        return photoClass;
    }

    public void setPhotoClass(String photoClass) {
        this.photoClass = photoClass;
    }
}
