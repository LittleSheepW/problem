package com.ww.July.twentyfour.main;

import com.ww.jdk8.interfaces.Jdk8Interface;
import com.ww.pojo.Student;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author: Sun
 * @create: 2019-07-24 15:21
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        /*anonymousInnerClass(new Jdk8Interface() {
            @Override
            public void abstractMethod() {
                System.out.println("I'm anonymousInnerClass");
            }

            {
                System.out.println("我先执行");
            }
        });*/

        // doubleBraceInitialization();
    }

    /**
     * 不能改变被捕获变量的的引用地址，但是其内部的属性是可以修改的  对应README 0724 第1个问题
     * @param integer
     * @param string
     * @param studentList
     * @param student
     */
    public static void lambdaVariableChange(Integer integer, String string, List<Student> studentList, Student student) {
        ActionListener listener = event -> {
            studentList.add(new Student());
            // studentList = new ArrayList<>();  Compile error：Variable used in lambda expression should be final or effectively final

            student.setId(1);
            // student = new Student();   Compile error：Variable used in lambda expression should be final or effectively final

           // integer = 666;  Compile error：Variable used in lambda expression should be final or effectively final

           // string = "change";  Compile error：Variable used in lambda expression should be final or effectively final
        };
    }

    /**
     * 双大括号初始化语法，可用于任意对象中(Object HashMap ArrayList)
     */
    public static void doubleBraceInitialization() {
        /**
         * 如果构造参数的闭小括号后面跟一个开大括号，正在定义的就是匿名内部类。
         * 下例中：
         * 使用了双括号初始化(double brace initialization), 这里利用了内部类语法。
         * 外层{} 建立了Student的一个匿名子类的一个匿名对象
         * 内层{} 为初始块，只要构造类的对象，这些块就会被执行
         */
        Student student1 = new Student() {{
            setId(1);
            setName("sun")  ;
        }};
        System.out.println(student1.getClass().getName());  // 当前student对象实际类型并不是Student类型，而是Student类的子类

        Student student2 = new Student();
        System.out.println(student2.getClass().getName()); // 当前student对象实际类型为Student类型
    }

    /**
     * 使用匿名内部类简化传递接口的操作。在调用该方法同时加入{}构造块测试语法是否可以通过。
     * @param jdk8Interface
     */
    public static void anonymousInnerClass(Jdk8Interface jdk8Interface) {
        jdk8Interface.abstractMethod();
    }
}
