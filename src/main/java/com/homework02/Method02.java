package main.java.com.homework02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 方法2：
 */

public class Method02 {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        int threadNumber = 1;
        final CountDownLatch cdl = new CountDownLatch(threadNumber);
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        AtomicInteger result = new AtomicInteger();
        Thread t = new Thread(() -> {
            result.set(sum()); //这是得到的返回值
            System.out.println("子线程结束");
            cdl.countDown();
        });

        t.start();
        //线程启动后调用countDownLatch方法
        try {
            cdl.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
        System.out.println("退出main线程");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
