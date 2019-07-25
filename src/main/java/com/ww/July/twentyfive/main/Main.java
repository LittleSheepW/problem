package com.ww.July.twentyfive.main;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Comparator;

/**
 * @author: Sun
 * @create: 2019-07-25 11:11
 * @version: v1.0
 */
public class Main {

    public static void main(String[] args) {
        // systemOutObject();
        generateDynamicProxyClassCode();
    }

    // (我使用Sysetem.out获取PrintStream对象，点击源码时我发现这个变量修饰符为static final并且为null) 引发了 0725 第3个问题
    public static void systemOutObject() {
        PrintStream out = System.out;
        System.out.println(out);
    }

    /**
     * 生成动态代理类的class文件
     */
    public static void generateDynamicProxyClassCode() {
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[] {Comparable.class, Comparator.class});
        String path = "/Users/sun/devtools/IdeaProjects/IntegerProxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(bytes);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("写文件错误");
        }
    }
}
