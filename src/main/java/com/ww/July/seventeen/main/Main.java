package com.ww.July.seventeen.main;

import com.ww.pojo.Student;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author: Sun
 * @create: 2019-07-17 10:25
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        // getClassObjectUseEquals();
        // reflectionGetObject();
        // reflectionCreateObject();
        // reflectPackageClasses();
        // fieldGetMethod();
        // badArrayGrow(new Student[] {new Student(1, "sun")});

        /*Object o = goodArrayGrow(new Student[] {new Student(1, "sun")});
        // 将一个Student[]临时转为Object[]数组，然后再转换回Student[]是没问题的。但是如果一开始就是Object[]类型的，是无法直接转换为Student[]
        Student[] o1 = (Student[]) o;
        System.out.println(Arrays.toString(o1));*/

        useReflectMethodInoke();
    }

    /**
     * 虚拟机为每个类型管理一个Class对象，可以使用==比较两个类对象。
     */
    public static void getClassObjectUseEquals() {
        Class<Student> studentClass1 = Student.class;
        Class<? extends Student> studentClass2 = new Student().getClass();

        System.out.println(studentClass1 == studentClass2);
    }

    /**
     * 获取class对象的三种方式
     */
    public static void reflectionGetObject() {
        Class<Student> studentClass1 = Student.class;
        Class<? extends Student> studentClass2 = new Student().getClass();
        try {
            Class<?> studentClass3 = Class.forName("com.ww.pojo.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射创建对象
     */
    public static void reflectionCreateObject() {
        try {
            /**
             * 查看newInstance()方法源码：获取对应类对象的空参构造方法(得到Constructor对象) --> 检查构造方法访问权限 --> Constructor对象调用newInstance()方法
             *
             * newInstance()方法可以获取到私有构造方法，但是在运行构造方法创建对象之前会进行检查。所以使用instance()方法时，
             * 必须保证构造方法的不为private/protected/default如有其他特殊情况请说明。Class对象直接调用newInstance()方法
             * 走的是该类的无参构造方法
             *
             * 使用类对象getConstructor()或getConstructors()可以获取到指定的构造方法在进行调用newInstance()，
             * Constructor对象调用newInstance()方法走的是对应获取到的构造方法
             */
            Class<Student> studentClass1 = Student.class;

            // Class类对象调用newInstance()
            Student student2ClassInstance = studentClass1.newInstance();

            // 通过Class对象先获取对应的构造方法，再通过Constructor对象调用newInstance()方法
            Constructor<Student> constructor = studentClass1.getConstructor();
            Student student2ConstructorInstance = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * java.lang.reflect包下的类
     * Constructor、Field、Method类
     * 这三个类都有getName()，用于返回构造方法(包名+类名)、字段、方法名称
     *           getModifiers()方法，返回整型数值：描述修饰符使用情况。
     * Field类有一个getType方法，用来返回当前域对应的class对象。
     * Method、Constructor类有能报告参数类型方法。
     * Method类还有一个可以报告返回类型的方法。
     */
    public static void reflectPackageClasses() {
        Class<Student> studentClass1 = Student.class;
        Constructor<?>[] constructors = studentClass1.getConstructors();
        Field[] fields = studentClass1.getFields();
        Method[] methods = studentClass1.getMethods();

        System.out.println("---------------Constructor class method getName()-----------------");
        for (Constructor<?> constructor : constructors) {
            // Constructor getName方法  result:com.ww.pojo.Student
            System.out.println(constructor.getName());
        }

        System.out.println("---------------Field class method getName()-----------------");
        for (Field field : fields) {
            System.out.println(field.getName() + "..." + field.getType().getName());
        }

        System.out.println("---------------Method class method getName()-----------------");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    /**
     * 查看对象域的方法是Field类中的get方法和set方法如何使用
     * 下例中：id是一个Field类型的对象  sun是包含id域的对象
     */
    public static void fieldGetMethod() {
        Student sun = new Student(1, "sun");
        System.out.println(sun);
        Class<? extends Student> aClass = sun.getClass();
        try {
            Field id = aClass.getDeclaredField("id");
            // 返回指定对象上此Field表示的字段的值(基本类型自动包装为包装类型)
            Object o = id.get(sun);
            System.out.println(o);

            // 修改相应域的值
            id.set(sun, 2);
            System.out.println(sun);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 扩展泛型数组(错误的示范，无法强转为原来的类型)
     * @param a
     * @return
     */
    public static Object[] badArrayGrow(Object[] a) {
        int newLength = a.length * 11 / 10 + 10;
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, a.length);
        return newArray;
    }

    /**
     * 通过反射扩展泛型数组(可以正常转换为原来的类型，不论对象或基本类型)
     * @param a
     * @return
     */
    public static Object goodArrayGrow(Object a) {
        Class cl = a.getClass();
        // result: [Lcom.ww.pojo.Student;
        System.out.println(cl.getName());
        if (!cl.isArray()) return null;

        // 获取数组组件类型的Class对象
        Class componentType = cl.getComponentType();
        // result: com.ww.pojo.Student
        System.out.println(componentType.getName());

        int length = Array.getLength(a);
        int newLength = length * 11 / 10 + 10;
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, length);
        return newArray;
    }

    /**
     * 使用反射中Method类的invoke方法
     * public Object invoke(Object implicitParameter, Object[] explicitParameters)调用这个对象所描述的方法，
     * 传递给定参数，并返回方法的返回值。对于静态方法，把null作为隐式参数传递。在使用包装器传递基本类型的值时，基本类型的返回值必须是未包装的。
     */
    public static void useReflectMethodInoke() {
        Class<Main> mainClass = Main.class;
        try {
            Method test = mainClass.getMethod("test");
            Object invoke = test.invoke(new Main());
            System.out.println(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String test() {
        return "Hello reflect";
    }
}
