package com.muyi.courage.demoForSingleton;

/**
 * @author 杨志勇
 * @date 2021-02-23 10:59
 */
public class SingletonHungaryTest {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        Mythread mythread1 = new Mythread();
        Mythread mythread2 = new Mythread();
        Mythread mythread3 = new Mythread();

        mythread.start();
        mythread1.start();
        mythread2.start();
        mythread3.start();

    }

    static  class Mythread extends Thread{
        @Override
        public void run(){
            System.out.println(SingletonHungary.getInstance().hashCode());
        }
    }
}
