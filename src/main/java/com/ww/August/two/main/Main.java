package com.ww.August.two.main;

import com.ww.pojo.Student;
import com.ww.pojo.father.Father;

import java.util.*;

/**
 * @author: Sun
 * @create: 2019-08-02 10:41
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        // iterator();
        // collectionContainsMethod();
        // listIteratorMethod();
        // testTreeSetAndHashSetAddSameElement();

    }

    /**
     * Iterator.forEachRemaining()
     */
    public static void iterator() {
        List<Father> fatherList = new ArrayList<>();
        fatherList.add(new Father(1, "sun"));
        fatherList.add(new Father(2, "ran"));

        Iterator<Father> iterator = fatherList.iterator();
        // 使用迭代器forEachRemaining()方法遍历迭代器
        iterator.forEachRemaining(System.out::println);
    }

    /**
     * Collection.contains()
     */
    public static void collectionContainsMethod() {
        List<Father> fatherList = new ArrayList<>();
        Father father = new Father(1, "sun");
        fatherList.add(father);

        System.out.println(fatherList.contains(father));
    }

    /**
     * LinkedList.listIterator()
     */
    public static void listIteratorMethod() {
        List<Student> studentList = new LinkedList<>();
        studentList.add(new Student(1, "sunshine"));
        studentList.add(new Student(2, "ran"));

        ListIterator<Student> studentListIterator = studentList.listIterator();
        while (studentListIterator.hasNext()) {
            System.out.println("nextIndex:" + studentListIterator.nextIndex());
            System.out.println(studentListIterator.next());
        }

        System.out.println("---");

        while (studentListIterator.hasPrevious()) {
            System.out.println("previousIndex:" + studentListIterator.previousIndex());
            System.out.println(studentListIterator.previous());
        }
    }

    /**
     * 测试TreeSet和HashSet是否可以添加重复元素，结果为都不可以重复添加相同元素
     */
    public static void testTreeSetAndHashSetAddSameElement() {
        Set<Integer> hashSet = new HashSet();
        TreeSet<Integer> treeSet = new TreeSet();

        hashSet.add(1);
        hashSet.add(1);

        treeSet.add(1);
        treeSet.add(1);

        System.out.println(hashSet);
        System.out.println(treeSet);
    }
}
