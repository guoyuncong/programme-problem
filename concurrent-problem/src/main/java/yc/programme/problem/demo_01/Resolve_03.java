package yc.programme.problem.demo_01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 十个线程轮流打印 A B C，打印 10 次
 * ❸ 用 ReentrantLock、1 个Condition 实现
 *
 * @author: yagena
 * @date: 2021-03-01
 */
public class Resolve_03 {

    static int index = 1;

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10; i++) {
                        while (index % 3 != 1) {
                            condition.await();
                        }
                        System.out.println("a");
                        index++;
                        condition.signalAll();
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
                        while (index % 3 != 2) {
                            condition.await();
                        }
                        System.out.println("b");
                        index++;
                        condition.signalAll();
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
                        while (index % 3 != 0) {
                            condition.await();
                        }
                        System.out.println("c");
                        index++;
                        condition.signalAll();
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
    }

}
