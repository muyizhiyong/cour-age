package com.muyi.courage.demoForSingleton;

/**
 * @author 杨志勇
 * @date 2021-02-23 14:36
 */
public class SingletonLazy2 {
    private static SingletonLazy2 singletonLazy2;

    private SingletonLazy2(){

    }
    //通过设置同步代码块，使用DCL双检查锁机制
    //使用双检查锁机制成功的解决了单例模式的懒汉实现的线程不安全问题和效率问题
    //DCL 双重锁定检查（DCL,Double Check Lock） 也是大多数多线程结合单例模式使用的解决方案
    public static SingletonLazy2 getInstance(){
        try {
            if (null == singletonLazy2){
                Thread.sleep(1000);
                synchronized (SingletonLazy2.class){
                    if(null == singletonLazy2){
                        singletonLazy2 =new SingletonLazy2();
                    }
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return singletonLazy2;
    }
}
