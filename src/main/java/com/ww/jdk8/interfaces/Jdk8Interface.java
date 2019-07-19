package com.ww.jdk8.interfaces;

/**
 * Jdk8 接口新特性
 *
 * @author: Sun
 * @create: 2019-07-19 22:06
 * @version: v1.0
 */
public interface Jdk8Interface {

    void abstractMethod();

    /**
     * JDK8接口新特性：静态方法
     * 实现你自己的接口时，不再需要为实用工具方法另外提供一个伴随类
     * @param param
     * @return
     */
    static String staticMethod(String param) {
        System.out.println(param);
        return "Hello, static method!";
    }

    /**
     * JDK8接口：默认方法(保证了源代码兼容)
     * 每一个实现类都要覆盖这个方法，看起来默认方法并没有什么用。
     * 有这样的场景：你需要实现一个接口，接口中包含5个方法，那么就要实现这5个方法，但是你只关心其中
     * 的1、2个方法。这时其他不用关心的方法可以声明为默认方法。(类实现这个接口时可以选择性的进行覆盖该默认方法)
     * @param param
     * @return
     */
    default String defaultMethod(String param) {
        System.out.println(param);
        return "I'm default method";
    }
}
