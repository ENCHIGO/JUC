package com.enchigo.juc.learning;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author enchigo
 * @version V1.0
 * @Package com.enchigo.juc.learning
 * @Description: TODO
 * @date 2020/7/28-5:46 下午
 */

public class ConditionProducerConsumer {

    final static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        lock.lock();
    }




}
