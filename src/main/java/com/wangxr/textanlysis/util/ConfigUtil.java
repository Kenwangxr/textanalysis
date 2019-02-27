package com.wangxr.textanlysis.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读取
 */
public class ConfigUtil extends Properties {

    private static final Properties properties = new Properties();
    static {

        try {
            properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            System.out.println("读取数据库配置出错");
        }
    }

    public static void main(String[] args) {
        ConfigUtil configUtil = new ConfigUtil();
        configUtil.getDatabase();
    }

    public  static String getDatabase(){
        return properties.getProperty("database");
    }

    public static String getUsername(){
        return properties.getProperty("username");
    }

    public static String getPassword(){
        return properties.getProperty("password");
    }
}
