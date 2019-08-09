package com.ww.August.eight.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: Sun
 * @create: 2019-08-08 14:30
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // writePropertiesFile();
        // readPropertiesFile();

        System.out.println(System.getProperty("user.home"));
    }

    public static void writePropertiesFile() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name", "sunshine");
        properties.setProperty("age", "20");

        FileOutputStream outputStream = new FileOutputStream("my.properties");
        properties.store(outputStream, "I'm properties file");
    }

    /**
     * 出于历史上的原因，Properties类实现了Map<Object,Object。因此可以使用Map接口的get和put方法。
     * 不过get方法会返回类型Object，而put方法允许插入任何对象。最好坚持使用getProperty和setProperty方法，这些方法会处理字符串，而不是对象。
     * @throws IOException
     */
    public static void readPropertiesFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("my.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        System.out.println(properties);

        System.out.println(properties.getProperty("name"));
        System.out.println(properties.getProperty("sun", "sunshine"));
    }
}