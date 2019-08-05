package com.ww.August.five.main;

import java.util.*;

/**
 * @author: Sun
 * @create: 2019-08-05 11:22
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        // studyArraysAsListMethod();
        // subrange();
        // viewedView();
        // testCollectionsSingletonMethod();
        arrayAndListReverse();
    }

    /**
     * 轻量级集合包装器(视图对象)
     * 不可以对返回的集合对象进行改变数组大小的方法
     */
    public static void studyArraysAsListMethod() {
        List<String> list = Arrays.asList(new String[]{"sun", "zhong", "wei"});
        // java.util.Arrays$ArrayList
        System.out.println(list.getClass().getName());
        list.set(2, "ran");
        System.out.println(list);
        // list.remove(2);   throw UnsupportedOperationException

        List<String> settings = Collections.nCopies(100, "DEFAULT");
        // settings.set(2, "sunzhognwei");  throw UnsupportedOperationException
        System.out.println(settings);
        System.out.println(settings);
        // java.util.Arrays$ArrayList
        System.out.println(list.getClass().getName());
        // settings.remove(2);   throw UnsupportedOperationException
    }

    /**
     * 子范围，对子范围对象进行的非结构更改会映射至对应的父级对象
     */
    public static void subrange() {
        List<String> stringList = new ArrayList<String>() {{
            add("sun");
            add("zhong");
            add("wei");
            add("liu");
            add("ran");
        }};
        System.out.println(stringList);
        System.out.println(stringList.getClass().getName());

        // 通过subList()方法截取
        List<String> subList = stringList.subList(2, 5);
        System.out.println(subList);
        System.out.println(subList.getClass().getName());
        /**
         * AbstractList类中remove()方法调用了SubList类中removeRange()方法，
         * 该方法又调用父级(ArrayList)的removeRange()方法，所以对该(SubList)集合类型
         * 的非结构性更改都会映射到父级上
         */
        subList.clear();

        System.out.println(stringList);
    }


    /**
     * 同步视图：Collections类的静态synchronizedMap方法可以将任何一个映射表转换成具有同步访问方法的Map
     */
    public static void synchronizedMap() {
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());

    }

    /**
     * 受查视图：对泛型类型发生问题时提供调试支持。
     * 受查视图受限于虚拟机可以运行的运行时检查。例如，对于ArrayList<Pair<String>>，由于虚拟机有一个单独的“原始”Pair类，所以无法阻止插入Pair<Date>
     */
    public static void viewedView() {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList rawList1 = strings;
        rawList1.add(new Date());    // 错误类型的元素混人泛型集合中
        // System.out.println(strings.get(0));     // throw java.util.Date cannot be cast to java.lang.String

        /**
         * Collections.checkedList() 返回指定列表的动态类型安全视图。
         * 任何插入错误类型元素的尝试都会导致立即发生ClassCastException。
         */
        List<String> stringList = Collections.checkedList(strings, String.class);
        List rawList2 = stringList;
        rawList2.add(new Date());
    }

    /**
     * 测试Collections.singleton()方法
     */
    public static void testCollectionsSingletonMethod() {
        // 返回仅包含指定对象的不可变集
        Set<String> sun = Collections.singleton("sun");
        System.out.println(sun);
    }

    /**
     * 学习Collections.removeAll()方法
     */
    public static void studyCollectionsRemoveAllMethod() {
        Map<String, Object> stringObjectMap = new HashMap<String, Object>() {{
            put("1", "sunshine1");
            put("2", "sunshine2");
            put("3", "sunshine3");
            put("4", "sunshine4");
            put("5", "sunshine5");
        }};

        System.out.println(stringObjectMap);

        Set<String> stringSet = new HashSet<>();
        stringSet.add("1");
        stringSet.add("2");
        stringSet.add("3");

        stringObjectMap.keySet().removeAll(stringSet);
        System.out.println(stringObjectMap);
    }

    /**
     * 数组集合相互转换
     */
    public static void arrayAndListReverse() {
        String[] values = {"sun", "zhong", "wei"};
        System.out.println(Arrays.toString(values));

        Set<String> stringHashSet = new HashSet<>(Arrays.asList(values));
        System.out.println(stringHashSet);

        Object[] objects = stringHashSet.toArray();
        System.out.println(Arrays.toString(objects));

        // String[] strings = (String[]) stringHashSet.toArray();   throw ClassCastException
        String[] strings = stringHashSet.toArray(new String[stringHashSet.size()]);
        System.out.println(Arrays.toString(strings));
    }
}
