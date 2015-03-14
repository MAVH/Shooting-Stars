package com.shooting_stars.project.command;

import com.opensymphony.xwork2.ActionSupport;
import com.shooting_stars.project.entity.User;
import com.shooting_stars.project.exception.CommandException;
import com.shooting_stars.project.manager.ConfigManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Пользователь on 04.03.2015.
 */
public class ChangePhotoCommand extends ActionSupport {
    static Logger logger = Logger.getLogger(ChangePhotoCommand.class);
    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String execute() {
        String saveFile="";
        HttpServletRequest request = ServletActionContext.getRequest();
        String contentType = request.getContentType();
        String result = SUCCESS;
        try {
            if((contentType != null)&& (contentType.indexOf("multipart/form-data") >= 0)) {
                DataInputStream in = new DataInputStream(request.getInputStream());
                int formDataLength = request.getContentLength();
                byte dataBytes[] = new byte[formDataLength];
                int byteRead = 0;
                int totalBytesRead = 0;
                while (totalBytesRead < formDataLength) {
                    byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                    totalBytesRead += byteRead;
                }
                String file = new String(dataBytes);
                saveFile = file.substring(file.indexOf("filename=\"") + 10);
                saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
                saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
                int lastIndex = contentType.lastIndexOf("=");
                String boundary = contentType.substring(lastIndex + 1, contentType.length());
                int pos;
                pos = file.indexOf("filename=\"");
                pos = file.indexOf("\n", pos) + 1;
                pos = file.indexOf("\n", pos) + 1;
                pos = file.indexOf("\n", pos) + 1;
                int boundaryLocation = file.indexOf(boundary, pos) - 4;
                int startPos = ((file.substring(0, pos)).getBytes()).length;
                int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
                System.out.println(saveFile);
                /*
                User user = (User)request.getSession().getAttribute("user");
                //different formats
                saveFile = user.getUserId() + "jpg";
                 */

                //adding url to database
                User user = (User)request.getSession().getAttribute("user");
                saveFile = user.getUserId() + ".jpg";
                File ff = new File(ServletActionContext.getServletContext().getContextPath() + "/img/userPhoto/", saveFile);
                FileOutputStream fileOut = new FileOutputStream(ff);
                fileOut.write(dataBytes, startPos, (endPos - startPos));
                fileOut.flush();
                fileOut.close();
                HttpSession session = request.getSession();
                session.setAttribute("photoURL","/img/userPhoto"+ saveFile);
            } } catch(IOException e) {
                exception =  new CommandException("Problem with stream", e);
                logger.error(exception.getMessage(), exception.getCause());
                result = ERROR;
        }
        return result;
    }
}
