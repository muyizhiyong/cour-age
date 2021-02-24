package com.muyi.courage.demoForSingleton;

/**
 * @author 杨志勇
 * @date 2021-02-23 11:19
 */
public class SingletonLazy1 {
    private static SingletonLazy1 singletonLazy1;

    private SingletonLazy1(){

    }
    // 设置同步方法效率太低
    // 整个方法被上锁
    synchronized public static SingletonLazy1 getInstance(){
        try {
            if (singletonLazy1 == null){
                Thread.sleep(1000);
                singletonLazy1 =new SingletonLazy1();
            }

        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return singletonLazy1;
    }
}
