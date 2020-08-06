package com.enchigo.juc.learning;

import java.util.logging.FileHandler;

/**
 * @author enchigo
 * @version V1.0
 * @Package com.enchigo.juc.learning
 * @Description: TODO
 * @date 2020/7/30-2:57 下午
 */


public class VolatileTest implements Runnable {

    private static long field = 0;

    @Override
    public void run() {
        int i = 0;
        while (true) {

                field = 1;
                i++;
                long temp = field;
                if (temp != 1L && temp != -1L) {

                    System.exit(0);
                }

                if (temp == 0) {

                    System.exit(0);
                }
                field = 0;
            }

        //System.out.println("运行正确");
    }

    public static void main(String[] args) throws InterruptedException {

        VolatileTest t1 = new VolatileTest();
        VolatileTest t2 = new VolatileTest();
        Thread thread1 = new Thread(t1,"线程1");
        Thread thread2 = new Thread(t2,"线程2");

        thread1.start();
        thread2.start();

    }
}
