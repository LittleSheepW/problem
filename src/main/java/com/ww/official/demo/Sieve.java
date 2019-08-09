package com.ww.official.demo;

import java.util.*;

/**
 * This program runs the Sieve of Erathostenes benchmark. It computes all primes up to 2,000,000.
 *
 * @author Cay Horstmann
 * @version 1.21 2004-08-03
 */
public class Sieve {
    public static void main(String[] s) {
        int n = 10;
        long start = System.currentTimeMillis();
        BitSet b = new BitSet(n + 1);
        int count = 0;
        int i;
        // 添加对应的素数
        for (i = 2; i <= n; i++)
            b.set(i);
        i = 2;
        // 将非素数位进行关闭
        while (i * i <= n) {
            if (b.get(i)) {
                count++;
                int k = 2 * i;
                while (k <= n) {
                    b.clear(k);
                    k += i;
                }
            }
            i++;
        }
        // 计算有多少个素数 (之前已经计算过一次count了，本次计算count的时候从之前已经计算过之后的值开始)
        while (i <= n) {
            if (b.get(i)) {
                count++;
            }
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " primes");
        System.out.println((end - start) + " milliseconds");
    }
}
