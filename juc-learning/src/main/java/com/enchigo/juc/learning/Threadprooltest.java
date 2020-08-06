package com.enchigo.juc.learning;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author enchigo
 * @version V1.0
 * @Package com.enchigo.juc.learning
 * @Description: TODO
 * @date 2020/8/6-4:48 下午
 */




public class Threadprooltest {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 100, TimeUnit.DAYS, new ArrayBlockingQueue<>(20));


        for(int i = 0;i<50;i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }



    }




}
