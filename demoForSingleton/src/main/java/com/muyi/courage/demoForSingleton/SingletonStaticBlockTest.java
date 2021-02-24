package com.muyi.courage.demoForSingleton;

/**
 * @author 杨志勇
 * @date 2021-02-23 17:34
 */
public class SingletonStaticBlockTest {
    public static void main(String[] args) {
        Thread1 mythread = new Thread1();
        Thread1 mythread1 = new Thread1();
        Thread1 mythread2 = new Thread1();
        Thread1 mythread3 = new Thread1();

        mythread.start();
        mythread1.start();
        mythread2.start();
        mythread3.start();
    }
    static class Thread1 extends Thread {
        @Override
        public void run(){
            System.out.println(SingletonStaticBlock.getInstance().hashCode());
        }
    }
}
