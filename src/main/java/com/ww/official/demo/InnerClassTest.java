package com.ww.official.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * This program demonstrates the use of inner classes.
 * @version 1.11 2015-05-12
 * @author Cay Horstmann
 */
public class InnerClassTest
{
   public static void main(String[] args)
   {
      TalkingClock clock = new TalkingClock(1000, true);
      clock.start();
      // clock.start(1000, true);  测试局部内部类需求

      // keep program running until user selects "Ok"
      JOptionPane.showMessageDialog(null, "Quit program?");
      System.exit(0);
   }
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock
{
   private int interval;
   private boolean beep;

   /**
    * Constructs a talking clock
    * @param interval the interval between messages (in milliseconds)
    * @param beep true if the clock should beep
    */
   public TalkingClock(int interval, boolean beep)
   {
      this.interval = interval;
      this.beep = beep;
   }

   /**
    * Starts the clock.
    */
   public void start()
   {
      ActionListener listener = new TimePrinter();
      /**
       * 可以采用如下语法格式更加明确地编写内部对象的构造器
       * ActionListener listener = this.new TimePrinter();
       * 在这里，最新构造的TimePrinter对象的外围类引用被设置为创建内部类对象的方法中的this引用。
       * 这是一种最常见的情况。通常，this限定词是多余的。不过，可以通过显式地命名将外围类引用设置为其他的对象。
       *
       * 例如，如果TimePrinter是一个公有内部类，对于任意的语音时钟都可以构造一个TimePrinter
       * TalkingClock jabberer = new TalkingClock(1000, true) ;
       * TalkingOock.TiiePrinter listener = jabberer.new TimePrinterO;
       */
      Timer t = new Timer(interval, listener);
      t.start();
   }

   /**
    * 局部内部类
    * @param interval
    * @param beep
    */
   public void start(int interval, boolean beep)
   {
      class TimePrinter implements ActionListener
      {
         @Override
         public void actionPerformed(ActionEvent event)
         {
            System.out.println("At the tone, the time is " + new Date());
            if (beep) Toolkit.getDefaultToolkit().beep();
         }
      }
      ActionListener listener = new TimePrinter();
      Timer t = new Timer(interval, listener);
      t.start();
   }

   public class TimePrinter implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent event)
      {
         System.out.println("At the tone, the time is " + new Date());
         if (beep) Toolkit.getDefaultToolkit().beep();
         // if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();  内部类的特殊语法规则：外部类引用
      }
   }
}
