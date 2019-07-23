package com.ww.July.ten.main;

import com.ww.pojo.son.FirstSon;
import com.ww.pojo.father.Father;
import com.ww.service.StudentService;
import com.ww.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Sun
 * @create: 2019-07-10 10:15
 * @version: v1.0
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        instanceofProblem();
    }

    /**
     * instanceof关键字用于判断前面的对象是否是后面的类，或者其子类、实现类的实例。 对应README 0709 第一个问题
     */
    public static void instanceofProblem() {
        FirstSon son = new FirstSon();
        boolean b1 = son instanceof Father;
        log.info("[main] [子类 instanceof 父类] [result:{}]", b1);

        Father father = new Father();
        boolean b2 = father instanceof FirstSon;
        log.info("[main] [父类 instanceof 子类] [result:{}]", b2);

        StudentService serviceImpl = new StudentServiceImpl();
        boolean b3 = serviceImpl instanceof StudentService;
        log.info("[main] [实现类 instanceof 接口] [result:{}]", b3);
    }
}
