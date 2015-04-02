package com.shooting_stars.project.logic;

import com.shooting_stars.project.exception.LogicException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Пользователь on 02.04.2015.
 */
public class UserLogic {
    public static String changePhotoURL(int userId, String destPath,String fileName, File photo) throws LogicException {
        System.out.println("Src File name: " + photo);
        System.out.println("path:    " + destPath);
        String extension = FilenameUtils.getExtension(fileName);
        fileName = userId + "." + extension;
        System.out.println("Dst File name: " + fileName);
        File directory = new File(destPath);
        boolean nameEquals = false;
        for (File file : directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                Pattern pattern = Pattern.compile(userId + "(\\.)\\p{Lower}+");
                return Pattern.matches(userId + "(\\.)\\p{Lower}+", pathname.getName());
            }
        })
                ) {
            if (file.getName().equals(fileName)) {
                nameEquals = true;
            }
            file.delete();
        }
        File destFile = new File(destPath, fileName);
        try {
            FileUtils.copyFile(photo, destFile);
            //чтобы не потерялось между redeploy
            //destFile = new File("E:/CourseProject/Shooting-Stars/web/img/userPhoto",fileName);
            //FileUtils.copyFile(photo,destFile);
        } catch (IOException e) {
            throw new LogicException("Problem with adding photo to folder");
        }
        if (!nameEquals) {
            System.out.println("Insert photoURL in database");
            //TODO insert into db
        }
        System.out.println(fileName);
        return fileName;
    }
}
