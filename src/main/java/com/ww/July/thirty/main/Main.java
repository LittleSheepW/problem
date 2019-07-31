package com.ww.July.thirty.main;

/**
 * @author: Sun
 * @create: 2019-07-30 18:38
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        // 泛型方法调用可以省去<String>类型参数
        // String sun = Main.<String>getMiddle("sun");
        String middle = Main.getMiddle("sun", "zhongwei", "liu", "ran");

    }


    /**
     * 泛型方法
     * @param a
     * @param <T>
     * @return
     */
    public static <T> T getMiddle(T... a) {
        System.out.println(a.getClass().getName());
        return a[a.length / 2];
    }
}
