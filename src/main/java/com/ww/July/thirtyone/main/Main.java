package com.ww.July.thirtyone.main;

import com.ww.pojo.father.Father;

/**
 * @author: Sun
 * @create: 2019-07-31 11:53
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        try {
            Father father = Father.class.newInstance();

            Class<Father> fatherClass = Father.class;
            fatherClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
