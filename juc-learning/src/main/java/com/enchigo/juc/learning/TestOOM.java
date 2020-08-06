/*
package com.enchigo.juc.learning;

import java.util.concurrent.CountDownLatch;

*/
/**
 * @author enchigo
 * @version V1.0
 * @Package com.enchigo.juc.learning
 * @Description: TODO
 * @date 2020/8/1-11:50 上午
 *//*

public class TestOOM {

    public static void main(String[] args) {

        for (int i = 0;; i++) {
            System.out.println("i = " + i);
            new Thread(new fengjiaqi()).start();
        }
    }

}

class fengjiaqi extends Thread {
    CountDownLatch cdl = new CountDownLatch(1);

    public fengjiaqi() {
        this.setDaemon(true);
    }

    @Override
    public void run()
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }
    }
}

*/
