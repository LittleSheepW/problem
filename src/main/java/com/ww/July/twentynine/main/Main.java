package com.ww.July.twentynine.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileSystemException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author: Sun
 * @create: 2019-07-29 15:08
 * @version: v1.0
 */
public class Main {

    private static final Logger myLogger = Logger.getLogger("com.ww.July.twentynine.main.Main");

    public static void main(String[] args) {
        /*try {
            throwPackageExceptionObject();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("--------");
            throwable.getCause().printStackTrace();
        }*/

        // stackTraceTest();

        // studyAssert();

        // myLogger.log(Level.INFO, "main方法运行");

    }

    /**
     * 抛出包装异常对象(适用于异常类型转换场景，相当于两个异常对象)
     *
     * @throws Throwable
     */
    public static void throwPackageExceptionObject() throws Throwable {
        try {
            new FileInputStream("/Users/Sun/devtools/idea");
        } catch (FileNotFoundException e) {
            Throwable se = new FileSystemException("文件系统异常");
            // 将此Throwable的原因初始化为指定值。此方法最多可以调用一次。它通常在构造函数内调用，或者在创建Throwable对象之后立即调用。
            se.initCause(e);
            throw se;
        }
    }

    /**
     * 使用StackTraceTest类 打印堆栈踪迹
     */
    public static void stackTraceTest() {
        Throwable t = new Throwable();

        t.printStackTrace();
        System.out.println("---");

        StringWriter out = new StringWriter();
        t.printStackTrace(new PrintWriter(out));
        String description = out.toString();
        System.out.println(description);
        System.out.println("---");

        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement frame : frames) {
            System.out.println(frame);
        }
        System.out.println("---");

        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Thread thread : map.keySet()) {
            StackTraceElement[] frames2 = map.get(thread);
            for (StackTraceElement stackTraceElement : frames2) {
                System.out.println(stackTraceElement);
            }
        }
    }


    /**
     * Java断言 assert
     */
    public static void studyAssert() {
        int x = 1;

        // 断言的两种形式
        assert x >= 1;

        assert x >= 2 : "x not >= 2";  // 表达式将被传入AssertionError的构造器，并转换成一个消息字符串
        System.out.println(x);
    }
}
