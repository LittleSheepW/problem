package com.ww.July.sixteen.main;

import com.ww.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author: Sun
 * @create: 2019-07-16 10:17
 * @version: v1.0
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws Throwable {
        // collectionInitialization();
        // doesTheArrayHaveDefaultValues();
        // listAddMethodAndSetMethodDifference();
        // wrapperObjectAgainAssignment();
        // getRandom();
        // exception();
        // npeException();
        // throwException(null);
    }

    /**
     * ArrayList集合指定初始容量进行初始化的时候 内部会new出一个指定初始容量的数组出来，
     * 但是我们为什么new完了不add元素，进行get元素的时候会数组越界呢？这是因为在ArrayList
     * 中有一个size的原因，这个size代表的是数组的当前非空长度。初始化的时候虽然new出指定
     * 初始容量的数组，但是并没有将初始容量赋值给size。所有get的时候会抛出异常。
     * 最终结论：集合完成初始化后是分配了内存空间的   对应README 0716 第1个问题
     *
     * 因为ArrayList继承AbstractCollection，此类重写了Object类中的toString()方法，而数组
     * 并没有重写toString()方法。    对应README 0716 第5个问题
     *
     * 为了节省内存，首先数组也是类 数组类是虚拟机自动生成的 继承自Object 本身没有任何字段和方法 实现了Cloneable和Serializable接口
     * 数组和arraylist的区别是 arraylist是一个类 是有限的 而数组理论上是无限的 有int数组 byte数组 string数组 任何类型 都可以对应到几百个数组
     * 一维数组 二维数组到最多255维数组 因此如果虚拟机实现把自动生成的数组类加一个重写后的toString方法 那么如果有大量的不同类型的数组
     * 将会增加大量的内存占用 因为重写的方法的字节码是在虚拟机内存中保存的   对应README 0716 第6个问题
     */
    public static void collectionInitialization() {
        List<Student> objects = new ArrayList<>();
        objects.add(new Student(1, "sun"));
        System.out.println(objects.getClass().getName());
        System.out.println(objects);
    }

    /**
     * 2、数组构造完成后有默认值  对应README 0716 第2个问题
     */
    public static void doesTheArrayHaveDefaultValues() {
        int[] ints = new int[10];
        // 打印内存空间地址
        System.out.println(ints);
        // 打印数值  [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(Arrays.toString(ints));

        Integer[] integers = new Integer[10];
        // 打印数值 [null, null, null, null, null, null, null, null, null, null]
        System.out.println(Arrays.toString(integers));

        System.out.println(ints[9]);
        System.out.println(integers[9]);
    }

    /**
     * add()方法为集合添加元素
     * set()方法为修改集合中已存在的元素
     *
     * set()方法不可以为元素为空的地址set值，会抛出IndexOutOfBoundsException
     * 对应README 0716 第3个问题
     */
    public static void listAddMethodAndSetMethodDifference() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "sun"));
        studentList.set(0, new Student(2, "ran"));

        System.out.println(studentList.get(0));
    }

    /**
     * Integer类对象属性无法修改(不可变对象的类称之为不可变类)，当二次赋值时会在堆中开辟一块新的内存空间，
     * 实际上二次赋值后integer变量指向的内存空间就改变了(虽然不可变对象属性无法更改，
     * 但是integer变量指向的内存地址是可以更改的)。如果这样写：
     * final Integer integer = new Integer(1);
     * integer = 2;   编译器就会报出警告不会通过
     * 对应README 0716 第4个问题
     */
    public static void wrapperObjectAgainAssignment() {
        Integer integer = new Integer(1);
        integer = 2;
        System.out.println(integer);
    }

    /**
     * 阿里巴巴编码规约:注意 Math.random() 这个方法返回是 double 类型，
     * 注意取值的范围 0≤x<1(能够 取到零值，注意除零异常)，如果想获取整数类型
     * 的随机数，不要将 x 放大 10 的若干倍然后 取整，直接使用 Random 对象的
     * nextInt 或者 nextLong 方法。
     */
    public static void getRandom() {
        double randomDouble = Math.random();
        int randomInt = new Random().nextInt();
    }

    /**
     * 阿里巴巴编码规约:Java 类库中定义的可以通过预检查方式规避的 RuntimeException
     * 异常不应该通过 catch 的方式来处理
     */
    public static void exception() {
        // 此为反例
        try {
            int i = 1 / 0;
            System.out.println(i);
        } catch (RuntimeException e) {
            System.out.println("异常被捕获");
        }
    }

    /**
     * 阿里巴巴编码规约: 出现NPE的场景
     * 1) 返回类型为基本数据类型，return 包装数据类型的对象时，自动拆箱有可能产生 NPE
     * 2) 数据库的查询结果可能为 null。
     * 3) 集合里的元素即使 isNotEmpty，取出的数据元素也可能为 null。
     * 4) 远程调用返回对象时，一律要求进行空指针判断，防止 NPE。
     * 5) 对于 Session 中获取的数据，建议进行 NPE 检查，避免空指针。
     * 6) 级联调用 obj.getA().getB().getC();一连串调用，易产生 NPE。
     */
    public static void npeException() {
        // 1)
        Integer integer = null;
        // return integer;

        // 3)
        ArrayList<Student> objects = new ArrayList<>();
        objects.add(null);
        CollectionUtils.isNotEmpty(objects);
        System.out.println(objects.size());
        Student student = objects.get(0);
        // student.toString();

        // 2) 4) 5) 6) 略...
    }

    /**
     * 阿里巴巴编码规约: 避免直接抛出 new RuntimeException()， 更不允许抛出 Exception 或者 Throwable
     * @param string
     * @return
     * @throws Exception
     */
    public static Integer throwException(String string) throws Throwable {
        // 此为反例
        if (StringUtils.isBlank(string)) {
            throw new Throwable("throw Throwable exception");
        }
        return 1;
    }
}
