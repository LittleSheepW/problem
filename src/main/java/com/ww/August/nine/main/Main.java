package com.ww.August.nine.main;

/**
 * @author: Sun
 * @create: 2019-08-09 17:19
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        beginThread();
    }

    public static void beginThread() {
        Thread thread = new Thread(() -> {
            System.out.println("I'm Runnable.");
        });

        thread.start();

        System.out.println(thread.isInterrupted());

        System.out.println(thread.getState());

    }
}
