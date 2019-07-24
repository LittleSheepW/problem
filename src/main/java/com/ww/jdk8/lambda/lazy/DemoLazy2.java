package com.ww.jdk8.lambda.lazy;

import java.text.SimpleDateFormat;
import java.util.Date;

interface BuilderMessage {
    String buildeMessage();
}

/**
 * Lambda 的更优写法案例分析
 * 1) 创建一个接口 BuilderMessage，接口中有一个抽象方法 String buildeMessage()，返回字符串。
 * 2) 创建类，创建静态方法用于输出日志信息：static void log(int level, BuilderMessage bulider)在方法内部判断如果是 1 级信息，打印日志信息，并调用接口中的 buildeMessage()方法拼接字符串。
 * 3) 在 main 函数中创建日期格式化类，调用 2 次 log()方法：1 级调用 1 次，2 级调用 1 次。log 方法的第 2 个参数使用 Lambda 表达式，方法体中使用 return 返回拼接的字符串。
 * 4) 第 1 次信息内容是："Rose: " + sdf.format(new Date()) + " 进行了转账操作"
 * 5) 第 2 次信息内容是："Jack: " + sdf.format(new Date()) + " 进行了取钱操作"
 *
 * 案例分析：
 * 在 Lambda 表达式方法体中返回字符串之前，
 * 先输出当前的级别，发现只输出了级别1，级别2并没有输出。
 * 即当级别为2时，没有对字符串进行拼接操作了，这样就避免了拼接字符串带来的额外开销。
 *
 * 延迟执行小结：
 * 只有在需要的时候才能运行代码，这是使用lambda表达式的一种情况。void log(int level, BuilderMessage bulider) 这段代码实际的运行效果分如下步骤：
 * 1) 通过 BuilderMessage 接口接受 lambda 表达式。
 * 2) 检查它是否应该被调用，即 level 是否等于 1。
 * 3) 在需要的时候调用它，bulider.buildeMessage()。
 */
public class DemoLazy2 {
    public static void log(int level, BuilderMessage bulider) {
        if (level == 1) {
            System.out.println(bulider.buildeMessage());
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        log(1, () -> {
            System.out.println("level 1");
            return "Rose: " + sdf.format(new Date()) + " 进行了转账操作";
        });
        log(2, () -> {
            System.out.println("level 2");
            return "Jack: " + sdf.format(new Date()) + " 进行了取钱操作";
        });
    }
}