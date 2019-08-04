package com.ww.August.four.main;

import com.ww.bridge.method.SubClass;
import com.ww.bridge.method.SuperClass;

/**
 * @author: Sun
 * @create: 2019-08-04 21:47
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        understandingBridgeMethod();
    }

    /**
     * 认识BridgeMethod
     */
    public static void understandingBridgeMethod() {
        SuperClass superClass = new SubClass();
        System.out.println(superClass.method("abc123"));    // 调用的是实际的方法
        System.out.println(superClass.method(new Object()));    // 调用的是桥接方法
    }
}
