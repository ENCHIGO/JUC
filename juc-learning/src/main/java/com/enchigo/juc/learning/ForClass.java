package com.enchigo.juc.learning;



/**
 * @author enchigo
 * @version V1.0
 * @Package com.enchigo.juc.learning
 * @Description: TODO
 * @date 2020/8/6-10:40 上午
 */
public class ForClass {


    public static void main(String[] args) {

        hha hha = new hha();
        System.out.println(hha);
        test(hha);
        System.out.println(hha);

    }

    static void test( hha s) {
        s = new hha();
    }

    static class hha{

    }

}
