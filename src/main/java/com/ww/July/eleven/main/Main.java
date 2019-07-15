package com.ww.July.eleven.main;

import com.ww.pojo.Student;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author: Sun
 * @create: 2019-07-11 11:24
 * @version: v1.0
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        // objectEqualsProblem();
        // floatNumCompare();
        // bigDecimalNum();
        // valueOfAndParseIntMethodDifference();
        // subListCastArrayListException();
        // mapUnsupportedOperationException();
        // listToArray();
        // arrayToList();
        // noGenericListAreCopiedToGenericList();
        // listRemoveElement();
        testMapPut();
    }


    /**
     * Objects.equals方法可以规避空指针异常，推荐使用此方式代替对象.equals方法的形式. 对应README 07.10 问题3
     */
    public static void objectEqualsProblem() {
        Student student1 = new Student(1, "sun");
        Student student2 = new Student(2, "ran");

        log.info("[objectEqualsProblem] [Objects.equals(student1, student2):{}]", Objects.equals(student1, student2));
    }


    /**
     * 阿里巴巴编码手册：浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用equals 来判断。
     * 浮点数采用“尾数+阶码”的编码方式，类似于科学计数法的“有效数字+指数”的表示方式。二进制无法精确表示大部分的十进制小数，
     */
    public static void floatNumCompare() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        if (a == b) {
            // 预期进入此代码快，但事实上a==b的结果为false
            log.info("[main] [a==b true]");
        }

        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        if (x.equals(y)) {
            // 预期进入此代码快，执行其它业务逻辑，但事实上equals的结果为false
            log.info("[main] [x.equals(y) true]");
        }

        // 解决方案(1) 指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的。
        float diff = 1e-6f;
        if (Math.abs(a - b) < diff) {
            log.info("[main] [Math.abs(a - b) < diff   true]");
        }

        // 解决方案(2) 使用BigDecimal来定义值，再进行浮点数的运算操作。
        BigDecimal c = new BigDecimal("1.0");
        BigDecimal d = new BigDecimal("0.9");
        BigDecimal e = new BigDecimal("0.8");

        BigDecimal result1 = c.subtract(d);
        BigDecimal result2 = d.subtract(e);
        if (result1.equals(result2)) {
            log.info("[main] [result1.equals(result2)  true]");

        }
    }

    /**
     * 阿里巴巴编码手册：为了防止精度损失，禁止使用构造方法BigDecimal(double)的方式把double值转化为BigDecimal对象。
     */
    public static void bigDecimalNum() {
        BigDecimal bigDecimal = new BigDecimal(0.1f);
        log.info("[bigDecimalNum] [bigDecimal:{}]", bigDecimal);

        // 优先推荐入参为String的构造方法，或使用BigDecimal的valueOf()
        BigDecimal recommend1 = new BigDecimal("0.1");
        BigDecimal recommend2 = BigDecimal.valueOf(0.1);

        log.info("[bigDecimalNum] [recommend1:{}] [recommend2:{}]", recommend1, recommend2);

    }

    /**
     * parseInt()返回的为int类型，valueOf()返回的是Integer类型   对应README 07.10 问题4
     */
    public static void valueOfAndParseIntMethodDifference() {
        String s = "123";
        // parseInt() String类型转化为int类型
        int parseIntResult = Integer.parseInt(s);
        // valueOf() String类型转换为Integer类型
        Integer valueOfResult = Integer.valueOf(s);

        log.info("[valueOfAndParseIntMethodDifference] [parseIntResult:{}] [valueOfResult:{}]", parseIntResult, valueOfResult);
    }

    /**
     *  阿里巴巴编码手册：ArrayList的subList结果不可强转成ArrayList，否则会抛出ClassCastException异常，
     *  subList返回的是ArrayList的内部类SubList，并不是ArrayList而是ArrayList的一个视图，对于SubList子列表的所有操作最终会反映到原列表上。
     */
    public static void subListCastArrayListException() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "sun"));
        studentList.add(new Student(2, "ran"));

        List<Student> subListResult = studentList.subList(0, 1);

        // Throw ClassCastException
        ArrayList<Student> result = (ArrayList<Student>) subListResult;
    }

    /**
     * 阿里巴巴编码手册：使用Map的方法keySet()/values()/entrySet()返回集合对象时，
     * 不可以对其进行添加元素操作，否则会抛出 UnsupportedOperationException 异常。
     */
    public static void mapUnsupportedOperationException() {
        Map<String, Object> map = new HashMap<>();
        map.put("sun", new Student());

        // Throw UnsupportedOperationException
        map.keySet().add("sun");
    }

    /**
     * 阿里巴巴编码手册：使用集合转数组的方法，必须使用集合的toArray(T[]array)，
     * 传入的是类型完全一致、长度为 0 的空数组。
     * 说明: 使用toArray带参方法，数组空间大小的length:
     * 1) 等于 0，动态创建与 size 相同的数组，性能最好。
     * 2) 大于 0 但小于 size，重新创建大小等于 size 的数组，增加 GC 负担。
     * 3) 等于 size，在高并发情况下，数组创建完成之后，size 正在变大的情况下，负面影响与上相同。
     * 4) 大于 size，空间浪费，且在 size 处插入 null 值，存在 NPE 隐患。
     */
    public static void listToArray() {
        List<String> list = new ArrayList<>(2);
        list.add("sun");
        list.add("ran");
        String[] array = list.toArray(new String[0]);
    }


    /**
     * 阿里巴巴编码手册：使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，
     * 它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
     */
    public static void arrayToList() {
        String[] str = new String[] { "sun", "ran" };
        List<String> list = Arrays.asList(str);

        // list.add("sun");   Throw UnsupportedOperationException

        for (String s : list) {
            System.out.println(s);
        }

        str[0] = "change";

        // 适配器模式，后台数据仍然是数组，数组数据改变这里随之改变
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 阿里巴巴编码手册：在无泛型限制定义的集合赋值给泛型限制的集合时，在使用集合元素时，需要进行 instanceof 判断，
     * 避免抛出 ClassCastException 异常。毕竟泛型是在 JDK5 后才出现，考虑到向前兼容，编译器是允许非泛型集合与泛型集合互相赋值。
     * Java中的泛型是伪泛型，仅仅在编译时期存在。
     */
    public static void noGenericListAreCopiedToGenericList() {
        List<String> generics = null;

        List notGenerics = new ArrayList(10);
        notGenerics.add(new Student());
        notGenerics.add(new Integer(1));

        generics = notGenerics;
        System.out.println(generics.get(0) instanceof String);

        // 此处抛出 ClassCastException 异常
        String string = generics.get(0);
    }

    /**
     * 阿里巴巴编码手册：不要在foreach循环里进行元素的remove/add 操作。remove元素请使用Iterator方式
     */
    public static void listRemoveElement() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();

        /**
         * 为什么把1换成2就会抛出ConcurrentModificationException？
         * foreach循环会走两个方法hasNext() 和next()，如果不想报错的话，只要不进next()方法就好了。
         * 不进入next()方法那么就要求hasNext()的方法返回false了，即cursor == size。其中cursor是Itr类（Iterator子类）中的一个字段，用来保存当前iterator的位置信息，从0开始。
         * cursor本身就是游标的意思，在数据库的操作中用的比较多。只要curosr不等于size就认为存在元素。由于Itr是ArrayList的内部类，因此直接调用了ArrayList的size字段，现阶段集合中只有两个
         * 数据，当我把第一个元素remove掉之后，size会-1，这时cursor == size，hasNext()方法返回false不进入next()方法中，所以不会报错。如果我们删除第二个元素，cursor != size 为true，所以
         * 会进入到next()方法，然后checkForComodification()导致抛出异常。
         */
        for (String item : list) {
            System.out.println(item);
            if ("1".equals(item)) {
                list.remove(item);
            }
        }

        /**
         * 为什么使用迭代器删除就不会出现问题？
         * 因为在迭代器remove()方法中是做了处理的expectedModCount = modCount;
         */
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }
        }
    }

    public static void testMapPut() {
        Map<Integer, Object> map1 = new HashMap<>();
        long map1StartTime = System.currentTimeMillis();
        for (int i = 0; i < 1024; i++) {
            map1.put(i, i);
        }

        Set<Map.Entry<Integer, Object>> entries = map1.entrySet();
        map1.forEach((integer, o) -> System.out.println(integer + "..." + o));
    }
}
