package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChangePhotoCommand extends ActionSupport {
    private File photo;
    private String photoContentType;
    private String photoFileName;
    private String destPath;
    private Exception exception;
    private String photoURL;

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
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

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String execute() {
        String result = SUCCESS;
        //TODO change path
        destPath = "E:/CourseProject/Shooting-Stars/web/img/userPhoto";
        try{
            System.out.println("Src File name: " + photo);
            System.out.println("Dst File name: " + photoFileName);

            File destFile  = new File(destPath, photoFileName);
            FileUtils.copyFile(photo, destFile);
            photoURL = "img/userPhoto/" + photoFileName;
            //TODO insert into db
            System.out.println(photoURL);

        } catch (IOException e) {
            exception = new CommandException("Problem with stream", e);
            LOG.error(exception.getMessage(), exception.getCause());
            result = ERROR;
        }
        return result;
    }
}
