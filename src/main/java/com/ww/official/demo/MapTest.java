package com.ww.official.demo;

import com.ww.pojo.Employee;

import java.util.HashMap;
import java.util.Map;

/**
 * This program demonstrates the use of a map with key type String and value type Employee.
 * @version 1.12 2015-06-21
 * @author Cay Horstmann
 */
public class MapTest
{
   public static void main(String[] args)
   {
      Map<String, Employee> staff = new HashMap<>();
      staff.put("144-25-5464", new Employee("Amy Lee"));
      staff.put("567-24-2546", new Employee("Harry Hacker"));
      staff.put("157-62-7935", new Employee("Gary Cooper"));
      staff.put("456-62-5527", new Employee("Francesca Cruz"));

      System.out.println(staff.getOrDefault("1", new Employee("sunshine")));
      System.out.println("---");

      // print all entries

      System.out.println(staff);

      // remove an entry

      staff.remove("567-24-2546");

      // replace an entry

      staff.put("456-62-5527", new Employee("Francesca Miller"));

      // look up a value
      System.out.println("---");
      System.out.println(staff.get("157-62-7935"));

      // iterate through all entries

      System.out.println("---");
      staff.forEach((k, v) ->
         System.out.println("key=" + k + ", value=" + v));
   }
}

