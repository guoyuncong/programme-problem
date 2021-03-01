package yc.programme.problem.demo_01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 十个线程轮流打印 A B C，打印 10 次
 * ❹ 用 ReentrantLock、三个 Condition 实现
 *
 * @author: yagena
 * @date: 2021-03-01
 */
public class Resolve_04 {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock.newCondition();
        final Condition condition3 = lock.newCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {
                        condition1.await();
                        System.out.println("a");
                        condition2.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {
                        condition2.await();
                        System.out.println("b");
                        condition3.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {
                        condition3.await();
                        System.out.println("c");
                        condition1.signal();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(1000);
            lock.lock();
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
