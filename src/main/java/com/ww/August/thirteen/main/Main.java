package com.ww.August.thirteen.main;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @author: Sun
 * @create: 2019-08-13 10:42
 * @version: v1.0
 */
public class Main {

    public static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {
        // futureTask();

        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService<String>(pool);

    }

    /**
     * 学习ThreadLocal用法
     */
    public static void studyThreadLocal() {
        SimpleDateFormat simpleDateFormat = dateFormat.get();

        SimpleDateFormat simpleDateFormat1 = dateFormat.get();
    }

    /**
     * 构造FutureTask对象，接收线程返回值
     */
    public static void futureTask() {
        Callable<String> callable = () -> "hello world";
        FutureTask<String> futureTask = new FutureTask(callable);
        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
