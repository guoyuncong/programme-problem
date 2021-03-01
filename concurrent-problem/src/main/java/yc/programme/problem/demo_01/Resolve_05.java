package yc.programme.problem.demo_01;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 十个线程轮流打印 A B C，打印 10 次
 * ❺ 用 Semaphore 实现
 *
 * @author: yagena
 * @date: 2021-03-01
 */
public class Resolve_05 {

    public static void main(String[] args) {
        final Semaphore semaphore1 = new Semaphore(1);
        final Semaphore semaphore2 = new Semaphore(0);
        final Semaphore semaphore3 = new Semaphore(0);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        semaphore1.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("a");
                    semaphore2.release();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        semaphore2.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("b");
                    semaphore3.release();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        semaphore3.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("c");
                    semaphore1.release();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
