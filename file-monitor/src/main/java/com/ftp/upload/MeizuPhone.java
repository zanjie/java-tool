package com.ftp.upload;

import com.java.util.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by zanjie on 2017/3/25.
 */
public class MeizuPhone {
    public static final String HOST = Config.getValue("ftp.remote.ip");
    public static final int PORT = Config.getValueInt("ftp.remote.port");
    public static int ID = 1;

    public static final String USER_NAME = Config.getValue("ftp.remote.username");
    public static final String PASSWORD = Config.getValue("ftp.remote.password");
    public static final String BASE_PATH = Config.getValue("ftp.remote.basepath");
    public static final String DIRECTORY = Config.getValue("ftp.remote.directory");

    static {
        System.err.println("ftp save path is " + BASE_PATH + DIRECTORY);
    }

    public static boolean upFile(File file) {
        try {
            boolean result = FtpUtil.uploadFile(HOST, PORT, USER_NAME, PASSWORD, BASE_PATH, DIRECTORY, getName(file), new FileInputStream(file));
            System.out.print(result ? "ok!\n" : "failed!\n");
            return result;
        } catch (FileNotFoundException e) {
            System.out.print("failed!\n");
            System.err.println(file.getAbsolutePath() + " not founded");
            return false;
        }
    }

    public static String getName(File file) {
        return (ID++) + file.getName().substring(file.getName().lastIndexOf("."));
    }


}
