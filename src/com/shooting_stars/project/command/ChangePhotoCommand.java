package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.exception.LogicException;
import com.shooting_stars.project.logic.UserLogic;
import com.shooting_stars.project.manager.MessageManager;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;

public class ChangePhotoCommand extends SessionAwareCommand implements ServletRequestAware {
    private File photo;
    private String photoContentType;
    private String photoFileName;
    private String destPath;
    private HttpServletRequest request = null;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    @Override
    public String execute() {
        String result = SUCCESS;
        MessageManager messageManager = (MessageManager)sessionAttributes.get("messageManager");
        if(photo == null) {
            errorMessage = messageManager.getMessage("message.photo.empty");
            return INPUT;
        }
        try{
            int currentUserId = getCurrentUserId();
            String path = request.getServletContext().getRealPath("/img/userPhoto");
            UserLogic.changePhotoURL(currentUserId,path,photoFileName,photo);
        } catch (LogicException e) {
            exception = new CommandException(e.getCause());
            LOG.error(exception.getMessage(), exception.getCause());
            result = ERROR;
        }
        return result;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
