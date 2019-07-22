package com.ww.pojo;

import java.util.Objects;

/**
 * 此类重写equals方法
 * @author: Sun
 * @create: 2019-07-11 14:55
 * @version: v1.0
 */
public class Student implements Cloneable {

    public Integer id;

    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Student() { System.out.println("无参构造方法");}

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student)super.clone();
    }

    public static void main(String[] args) {
        // protected 如果子父类位于同包中，子类对象可以访问父类的protected方法
        Son son = new Son();
        son.callFatherMethod();

        Father father = new Father();
        father.test1();
    }
}
