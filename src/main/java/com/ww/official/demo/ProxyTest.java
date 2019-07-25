package com.ww.official.demo;

import java.lang.reflect.*;
import java.util.*;

/**
 * This program demonstrates the use of proxies.
 * @version 1.00 2000-04-13
 * @author Cay Horstmann
 */
public class ProxyTest
{
   /**
    * 代理类的运行流程：
    * 1、首先需要实现InvocationHandler接口invoke方法，如果使用的频率不高也可以使用匿名内部类的方式。
    * 2、使用Proxy.newProxyInstance方法构建代理类
    *    1) 第一个参数为使用的类加载器，null为默认类加载器
    *    2) 第二个参数为需要代理类class对象数组
    *    3) 第三个参数为InvocationHandler接口的实现类对象
    *    4) 将proxy对象进行包装，然后使用包装对象进行调用方法，无论调用任意方法，最终都会走代理类的invoke方法
    */
   public static void main(String[] args)
   {
      Object[] elements = new Object[1000];

      // fill elements with proxies for the integers 1 ... 1000
      for (int i = 0; i < elements.length; i++)
      {
         Integer value = i + 1;

         // value为被代理对象
         InvocationHandler handler = new TraceHandler(value);

         /**
          * newProxyInstance()方法执行流程 (如有疑问直接查看源码 对比当前注释即可回忆起来)
          * (1) Class<?> cl = getProxyClass0(loader, intfs); 先生成接口的代理类(该代理类存在于内存中)
          * (2) final Constructor<?> cons = cl.getConstructor(constructorParams); 获取刚刚生成的代理类的指定参数的构造方法，参数为(InvocationHandler var1)
          * (3) cons.newInstance(new Object[]{h}); 通过代理类指定参数构造方法构造代理对象(h为InvocationHandler对象，该对象中持有被代理对象)
          * 代理对象持有InvocationHandler对象，InvocationHandler对象中持有被代理对象。
          *
          * 代理类的名字无法定义，Sun虚拟机中的Proxy类将生成一个以字符串$Proxy开头的类名。
          * 对于特定的类加载器和预设的一组接口来说，只能有一个代理类。
          * 也就是说使用同一个类加载器和接口数组调用两次newProxyInstance方法的话，那么只能够得到同一个代理类的两个对象。
          *
          * 可以利用Proxy.getProxyClass方法获得这个类: Class proxyClass = Proxy.getProxyClass(null, interfaces);
          * 可以通过Proxy类中的isProxyClass方法检测一个特定的Class对象是否代表一个代理类。
          */
         Object proxy = Proxy.newProxyInstance(value.getClass().getClassLoader(), value.getClass().getInterfaces(), handler);

         elements[i] = proxy;
      }

      // construct a random integer
      Integer key = new Random().nextInt(elements.length) + 1;

      /**
       * search for the key  Integer
       * Integer类实现了Comparable接口，实际上是compareTo方法调用了代理对象处理器的invoke方法
       */
      int result = Arrays.binarySearch(elements, key);

      // print match if found
      if (result >= 0) System.out.println(elements[result]);
   }
}

/**
 * An invocation handler that prints out the method name and parameters, then
 * invokes the original method
 */
class TraceHandler implements InvocationHandler
{
   private Object target;  // 被代理对象

   /**
    * Constructs a TraceHandler
    * @param t the implicit parameter of the method call
    */
   public TraceHandler(Object t)
   {
      target = t;
   }

   /**
    * @param proxy 动态代理对象
    * @param m 当前执行的方法
    * @param args 调用目标方法时传入的实参
    * @return
    * @throws Throwable
    */
   @Override
   public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
   {
      // print implicit argument
      System.out.print(target);
      // print method name
      System.out.print("." + m.getName() + "(");
      // print explicit arguments
      if (args != null)
      {
         for (int i = 0; i < args.length; i++)
         {
            System.out.print(args[i]);
            if (i < args.length - 1) System.out.print(", ");
         }
      }
      System.out.println(")");

      // invoke actual method
      return m.invoke(target, args);
   }
}
