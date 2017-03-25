package com.java.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by zanjie on 2017/3/25.
 */
public class Config {
    private static volatile Properties properties;

    static {
        loadProperties("config.properties");
    }

    public static <T> T getValue(String key) {
        return (T) properties.get(key);
    }

    public static int getValueInt(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }

    public synchronized static void loadProperties(String path) {
        try {
            properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(path), Charset.forName("utf-8")));
        } catch (FileNotFoundException e) {
            System.err.println("err =" + path + " not founded!");
            System.err.println("系统将退出");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("err =" + path + " is not valid!");
            System.err.println("系统将退出");
            System.exit(0);

        }
    }
}
