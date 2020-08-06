package com.enchigo.juc.learning;

/**
 * @author enchigo
 * @version V1.0
 * @Package com.enchigo.juc.learning
 * @Description: TODO
 * @date 2020/7/30-2:34 下午
 */
public class AtomicTest implements Runnable {

    private static long field = 0;

    private volatile long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public AtomicTest(long value) {
        this.setValue(value);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100000000000L) {
            AtomicTest.field = this.getValue();
            i++;
            long temp = AtomicTest.field;
            if (temp != 1L && temp != -1L) {
                System.out.println("出现错误结果" + temp);
                System.exit(0);
            }
        }
        System.out.println("运行正确");
    }

    public static void main(String[] args) throws InterruptedException {
        // 获取并打印当前JVM是32位还是64位的
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch+"-bit");
        AtomicTest t1 = new AtomicTest(1);
        AtomicTest t2 = new AtomicTest(-1);
        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);
        T1.start();
        T2.start();
        T1.join();
        T2.join();
    }

}
