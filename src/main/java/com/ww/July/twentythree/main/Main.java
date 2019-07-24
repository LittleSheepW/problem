package com.ww.July.twentythree.main;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author: Sun
 * @create: 2019-07-23 14:42
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        // new Timer(1000, System.out::println).start();   Method Reference
        // repeatMessage("Hello", 1000);
        // new Main().lambdaThis();
    }

    /**
     * lambda 表达式可以捕获外围作用域中变量的值。但是要确保所捕获的值是明确定义的，这里有一个重要的限制。在lambda表达式中，
     * 只能引用值不会改变的变量(如果是对象则在运行期间该对象内存地址不得发生改变)。之所以有这个限制是有原因的。如果在lambda表达式中改变变量，
     * 并发执行多个动作时就会不安全。lambda 表达式中捕获的变量必须实际上是最终变量(effectivelyfinal)实际上的最终变量是指，这个变量初始化之后就不会再为它赋新值。
     *
     * 在本例当中，这个lambda表达式有1个自由变量text，在这里就是字符串 "Hello"。我们说它被 lambda 表达式捕获。
     * @param text
     * @param delay
     */
    public static void repeatMessage(String text, int delay) {
        ActionListener listener = event -> System.out.println(text);
        new Timer(delay, listener).start();
    }

    /**
     * 在lambda表达式中，只能引用值不会改变的变量。
     * @param start
     * @param delay
     */
    /*public static void countDown(int start, int delay) {
        ActionListener listener = event -> {
            start--;
            System.out.println(start);  // Error: Can't mutate captured variable System.out.println(start) ;
        };
        new Timer(delay, listener).start();
    }*/

    /**
     * 如果在lambda表达式中引用变量，而这个变量可能在外部改变，这也是不合法的
     * @param text
     * @param count
     */
    /*public static void repeat(String text, int count) {
        for (int i = 1; i <= count; i++) {
            ActionListener listener = event -> System.out.println(i + ": " + text);   // Error: Cannot refer to changing i
            new Timer(1000, listener).start();
        }
    }*/

    /**
     * 在 lambda 表达式中声明一个与局部变量同名的参数是不合法的
     */
    /*public static void localVariable() {
        Path first = Paths.get("usr/bin");
        Comparator<String> comp = (first, second) -> first.length() - second.length();
    }*/

    /**
     * 在一个lambda表达式中使用this关键字时，表达式this.toString()会调用Main对象的toString方法，而不是ActionListener实例的方法。
     * 在lambda表达式中，this的使用并没有任何特殊之处。lambda表达式的作用域嵌套在lambdaThis方法中，与出现在这个方法中的其他位置一样，lambda表达式中this的含义并没有变化。
     */
    public void lambdaThis() {
        ActionListener listener = event -> System.out.println(this.toString());
        new Timer(1000, listener).start();
    }

    @Override
    public String toString() {
        return "Main{}";
    }
}
