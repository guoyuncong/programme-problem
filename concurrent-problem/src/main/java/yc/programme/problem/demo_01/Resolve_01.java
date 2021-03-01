package yc.programme.problem.demo_01;

/**
 * 十个线程轮流打印 A B C，打印 10 次
 *  ❶ 用 while 循环和变量实现
 * @author: yagena
 * @date: 2021-03-01
 */
public class Resolve_01 {

    public static int index = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (index > 30) {
                    break;
                }
                if (index % 3 == 1) {
                    System.out.println("a");
                    index++;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                if (index > 30) {
                    break;
                }
                if (index % 3 == 2) {
                    System.out.println("b");
                    index++;
                }
            }
        });
        Thread t3 = new Thread(() -> {
            while (true) {
                if (index > 30) {
                    break;
                }
                if (index % 3 == 0) {
                    System.out.println("c");
                    index++;
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
