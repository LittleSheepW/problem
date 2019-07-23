package com.ww.July.twentytwo.main;

import com.ww.entity.StudentEntity;
import com.ww.pojo.Student;

import java.util.Arrays;


/**
 * @author: Sun
 * @create: 2019-07-22 15:11
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        // implementsCloneableCloneObject();
        // cloneArray();
        new Main().testClone();

    }

    /**
     * 实现Cloneable接口实现深克隆
     */
    public static void implementsCloneableCloneObject() {
        Student student = new Student(1, "sun");
        System.out.println("student:" + student);
        try {
            Student cloneStudent = student.clone();
            System.out.println("cloneStudent" + cloneStudent);
            cloneStudent.id = 2;
            System.out.println("update cloneStudent" + cloneStudent);
            System.out.println("student:" + student);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  所有数组类型都有一个public的clone方法，而不是protected：可以用这个方法建立一个新数组，包含原数组所有元素的副本。
     */
    public static void cloneArray() {
        int[] luckyNumbers = { 2, 3, 5, 7, 11, 13 };
        System.out.println(Arrays.toString(luckyNumbers));

        int[] cloned = luckyNumbers.clone();
        System.out.println(Arrays.toString(cloned));

        cloned[0] = 666;
        System.out.println(Arrays.toString(cloned));
        System.out.println(Arrays.toString(luckyNumbers));
    }

    public void testClone() {
        try {
            Object clone = super.clone();
            System.out.println(clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
