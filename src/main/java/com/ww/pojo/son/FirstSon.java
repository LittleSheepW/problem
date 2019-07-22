package com.ww.pojo.son;

import com.ww.pojo.father.Father;

/**
 * @author: Sun
 * @create: 2019-07-22 19:13
 * @version: v1.0
 */
public class FirstSon extends Father {

    /**
     * class B继承了class A，但是两个类位于a、b两个不同的包中，此时class B直接访问class A中的protected修饰的方法。一共有三种方法访问：
     */
    void callFatherMethod() {
        // 直接调用
        father();

        // super关键字调用，其实前两种等同
        super.father();

        // new子类对象进行调用父类方法
        FirstSon son = new FirstSon();
        son.father();
    }
}
