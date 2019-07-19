package com.ww.jdk8.interfaces.impl;

import com.ww.jdk8.interfaces.Jdk8Interface;

/**
 * @author: Sun
 * @create: 2019-07-19 22:17
 * @version: v1.0
 */
public class Jdk8InterfaceImpl implements Jdk8Interface {

    @Override
    public void abstractMethod() {
        System.out.println("I'm abstract method");
    }
}
