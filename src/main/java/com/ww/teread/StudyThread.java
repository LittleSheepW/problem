package com.ww.teread;

/**
 * @author: Sun
 * @create: 2019-07-12 15:21
 * @version: v1.0
 */
public class StudyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("我是一个Runnable接口的实现类,我是一个线程。");
    }
}
