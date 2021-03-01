package yc.programme.problem.demo_01;

/**
 * 十个线程轮流打印 A B C，打印 10 次
 * ❷ 用 synchronized、wait、notifyAll 实现
 *
 * @author: yagena
 * @date: 2021-03-01
 */
public class Resolve_02 {

    private static int index = 1;

    public static void main(String[] args) {

        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        while (index % 3 != 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("a");
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        while (index % 3 != 2) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("b");
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        while (index % 3 != 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("c");
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

}
