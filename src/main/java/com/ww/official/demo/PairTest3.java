package com.ww.official.demo;


import com.ww.pojo.Employee;
import com.ww.pojo.Manager;
import com.ww.pojo.Pair;

/**
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class PairTest3
{
   public static void main(String[] args)
   {
      Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
      Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
      Pair<Manager> buddies = new Pair<>(ceo, cfo);
      printBuddies(buddies);

      ceo.setBonus(1000000);
      cfo.setBonus(500000);
      Manager[] managers = { ceo, cfo };

      Pair<Employee> result = new Pair<>();
      minmaxBonus(managers, result);
      System.out.println("first: " + result.getFirst().getName() 
         + ", second: " + result.getSecond().getName());
      maxminBonus(managers, result);
      System.out.println("first: " + result.getFirst().getName() 
         + ", second: " + result.getSecond().getName());
   }

   public static void printBuddies(Pair<? extends Employee> p)
   {
      /**
       * <? entends Employee>上限通配符为什么不可以使用setFirst()方法？
       * 编译器只知道需要某个Employee的子类型，但不知道具体是什么类型。它拒绝传递任何特定的类型，
       * 毕竟?不能用来匹配(如果可以设置类型的话，你可能在添加的过程中添加多个Employee的子类，这样的话
       * 在取值的时候还是会出现问题，所以在=一开始的时候就是禁止添加元素的)。
       */
      // p.setFirst(new Manager());
      Employee first = p.getFirst();
      Employee second = p.getSecond();
      System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
   }

   public static void minmaxBonus(Manager[] a, Pair<? super Manager> result)
   {
      /**
       * <? super Manager>下限通配符为什么可以使用setFirst()方法呢?
       * 因为系统不知道你会把?当成什么，所以只能设置成object，所以你取出元素的时候，取出的是object类。
       * 因为下限有保证，所以可以添加T和T的子类。你初始化的时候，初始化的是T的某个父类。
       * 你现在取元素，你希望取出的是你初始化的那个父类，因为你只能添加T和T的子类，所以你取出的元素，肯定可以赋值给那个父类。
       */
      result.setFirst(new Manager());
      Object first = result.getFirst();
      if (a.length == 0) return;
      Manager min = a[0];
      Manager max = a[0];
      for (int i = 1; i < a.length; i++)
      {
         if (min.getBonus() > a[i].getBonus()) min = a[i];
         if (max.getBonus() < a[i].getBonus()) max = a[i];
      }
      result.setFirst(min);
      result.setSecond(max);
   }

   public static void maxminBonus(Manager[] a, Pair<? super Manager> result)
   {
      minmaxBonus(a, result);
      PairAlg.swapHelper(result); // OK--swapHelper captures wildcard type
   }
   // Can't write public static <T super manager> ...
}

class PairAlg
{
   public static boolean hasNulls(Pair<?> p)
   {
      return p.getFirst() == null || p.getSecond() == null;
   }

   public static void swap(Pair<?> p) { swapHelper(p); }

   public static <T> void swapHelper(Pair<T> p)
   {
      T t = p.getFirst();
      p.setFirst(p.getSecond());
      p.setSecond(t);
   }
}


