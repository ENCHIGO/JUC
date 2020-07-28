package com.enchigo.juc.learning;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author enchigo
 * @version V1.0
 * @Package com.enchigo.juc.learning
 * @Description: TODO
 * @date 2020/6/28-4:48 下午
 */

@Slf4j
public class ProducerConsumer {

    static List<String> queues = Lists.newArrayList();

    static volatile boolean full =false;

    public static void main(String [] args){

        Thread producer1 = new Thread(()->{
                    while (true){
                       synchronized (queues){
                        if(!full){
                            String s = UUID.randomUUID().toString();
                            queues.add(s);
                            System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"正在生产"+"第"+(queues.indexOf(s)+1)+"个"+s);
                            if(queues.size()==10){
                                full=true;
                                queues.notifyAll();
                            }
                        }
                        else {
                            System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"没有东西可以被生产");
                            try {
                                queues.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }
        },"producer1");

        Thread producer2 = new Thread(()->{
            while (true){
                synchronized (queues){
                    if(!full){
                        String s = UUID.randomUUID().toString();
                        queues.add(s);
                        System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"正在生产"+"第"+(queues.indexOf(s)+1)+"个"+s);
                        if(queues.size()==10){
                            full=true;
                            queues.notifyAll();
                        }
                    }
                    else {
                        System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"没有东西可以被生产");
                        try {
                            queues.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        },"producer2");

        Thread consumer = new Thread(()->{
                        while (true){
                            synchronized (queues){
                            if(!full){
                                System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"没有东西可以被消费");
                                try {
                                    queues.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }else {

                                String s = queues.get(0);
                                System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"正在消费"+"第"+(queues.indexOf(s)+1)+"个"+s);
                                queues.remove(0);
                                if(queues.size()==0){
                                    full = false;
                                    queues.notifyAll();
                                }
                            }

                        }

                }

        },"consumer");
        producer1.start();
        producer2.start();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.start();

    }

}
