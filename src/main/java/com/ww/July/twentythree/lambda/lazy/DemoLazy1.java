package com.ww.July.twentythree.lambda.lazy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 案例需求：
 * 1) 有一个静态方法: void log(int level, String message)，当它为日志级别为 1 级的时候，打印出日志的信息。
 * 2) 在 main 函数中创建一个时间格式化的类，格式是：yyyy-MM-dd HH:mm:ss.SSS
 * 3) 在 main 函数中调用 2 次 log()方法，1 级调用 1 次，2 级调用 1 次。
 * 4) 第 1 次日志信息内容是："Rose: " + sdf.format(new Date()) + " 进行了转账操作"
 * 5) 第 2 次日志信息内容是："Jack: " + sdf.format(new Date()) + " 进行了取钱操作"
 *
 * 案例分析：
 * 1) 在 log 方法的内部设置断点，证明两次都会对字符串进行计算。
 * 2) 存在问题：字符串拼接完成之后，在调用 log 方法后可能不会被使用，那么此时之前的拼接工作就白做了，这样就会浪费性能。
 * 有些场景的代码执行后，结果不一定会被使用，从而造成性能浪费。
 */
public class DemoLazy1 {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        log(1, "Rose: " + sdf.format(new Date()) + " 进行了转账操作");
        log(2, "Jack: " + sdf.format(new Date()) + " 进行了取钱操作");
    }
    /**
     * ​
     * - 记录日志
     * - @param level 日志级别
     * - @param message 日志信息
     */
    public static void log(int level, String message) {
        if (level == 1) { // 用户日志
            System.out.println(message);
        }
    }
}