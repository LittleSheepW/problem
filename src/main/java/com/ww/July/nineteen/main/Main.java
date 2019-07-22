package com.ww.July.nineteen.main;

import com.ww.jdk8.interfaces.Jdk8Interface;
import com.ww.jdk8.interfaces.impl.Jdk8InterfaceImpl;

/**
 * @author: Sun
 * @create: 2019-07-19 22:04
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Jdk8Interface.staticMethod("transfer interface static method!"));
        System.out.println(new Jdk8InterfaceImpl().defaultMethod("transfer interface default method"));
    }
}
