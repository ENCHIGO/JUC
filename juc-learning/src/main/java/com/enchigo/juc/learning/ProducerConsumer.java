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


    public static void main(String [] args){

        Thread producer = new Thread(()->{

                while (true){
                    //System.out.println(Thread.currentThread().getName()+"开始生产");
                    synchronized (queues){
                       // System.out.println(Thread.currentThread().getName()+"被唤醒");
                    if(queues.size()<10){
                        String uuid = UUID.randomUUID().toString();
                        queues.add(uuid);
                        System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"生产第"+queues.size()+"个" +uuid );
                    }else {
                        try {
                            queues.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        },"producer");


        Thread consumer = new Thread(()->{

                while (true){
                    //System.out.println(Thread.currentThread().getName()+"开始消费");
                    synchronized (queues){
                        //System.out.println(Thread.currentThread().getName()+"被唤醒");
                        if(CollectionUtils.isEmpty(queues)){
                            System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"没有东西可以被消费");
                            try {
                                queues.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                        queues.forEach(s -> System.out.println(Thread.currentThread().getName() + "---"+Thread.currentThread().getId()+"正在消费"+"第"+(queues.indexOf(s)+1)+"个"+s));
                        queues.clear();
                        }
                    queues.notifyAll();
                }
            }
        },"consumer");

        producer.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.start();

    }

}
