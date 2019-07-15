package com.ww.teread;

import com.ww.pojo.Student;

import java.util.concurrent.Callable;

/**
 * @author: Sun
 * @create: 2019-07-12 15:21
 * @version: v1.0
 */
public class StudyCallable implements Callable<Student> {

    @Override
    public Student call() {
        System.out.println("我是一个Callable接口的实现类,我是一个线程。");
        return new Student(1, "callable学生");
    }
}
