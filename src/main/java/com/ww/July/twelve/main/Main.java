package com.ww.July.twelve.main;

import com.ww.pojo.Student;
import com.ww.teread.StudyCallable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author: Sun
 * @create: 2019-07-12 10:14
 * @version: v1.0
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        // testUseClassPathResourceGetFile();
        testCreateThread();
        // testSwitch(null);
        // automaticCloseStream();
    }

    /**
     * 通过ClassPathResource读取资源文件
     * Spring的配置文件读取通过ClassPathResource进行封装。
     */
    public static void testUseClassPathResourceGetFile() {
        try {
            File file = new File("application.properties");
            System.out.println(file.getName());

            // 使用ClassPathResource 从resources文件夹下读取对应的资源文件
            File file1 = new ClassPathResource("application.properties").getFile();
            System.out.println(file1.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阿里巴巴编码手册：不要通过Executors创建线程，使用ThreadPoolExecutor来进行创建线程。
     * 其实Executors创建线程的方法底层都是调用ThreadPoolExecutor，只不过做了很多限制
     */
    public static void testCreateThread() {
        // 创建线程的第一种方式
        StudyCallable studyCallable = new StudyCallable();

        /*try {
            Student call = studyCallable.call();
            System.out.println("studyCallable.call()");
            System.out.println(call.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // 创建线程的第二种方式
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

        try {
            Future fixedThreadPoolFuture = fixedThreadPool.submit(studyCallable);
            System.out.println("fixedThreadPool.submit()");
            System.out.println(fixedThreadPoolFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
        }

        /*// 创建线程的第三种方式
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1, 1, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPool.execute(() -> {
            try {
                Student threadPoolStudent = studyCallable.call();
                System.out.println("threadPool.execute()");
                System.out.println(threadPoolStudent);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                threadPool.shutdown();
            }
        });*/
    }

    /**
     * 阿里巴巴编码手册：在一个switch块内，每个case要么通过continue/break/return等来终止，
     * 要么注释说明程序将继续执行到哪一个case为止；在一个switch块内，都必须包含一个default语句
     * 并且放在最后，即使它什么代码也没有。
     * @param param
     */
    public static void testSwitch(String param) {
        if (StringUtils.isBlank(param)) {
            System.out.println("param is null!");
            return ;
        }
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }

    /**
     * 自动关流(IO流)
     */
    public static void automaticCloseStream() {
        File file = new File("/Users/sun/Documents/test2.json");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            System.out.println("使用JDK7自动关流");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
