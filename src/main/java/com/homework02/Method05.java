package main.java.com.homework02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 方法5：
 */

public class Method05 {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        BlockingQueue blockQueue = new ArrayBlockingQueue(1);
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        AtomicInteger result = new AtomicInteger();
        Thread t = new Thread(() -> {
            result.set(sum()); //这是得到的返回值
            System.out.println("子线程结束");
            try {
                blockQueue.put("结束");//在队列中加入数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        try {
            blockQueue.take();
        } catch (InterruptedException e) {
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
