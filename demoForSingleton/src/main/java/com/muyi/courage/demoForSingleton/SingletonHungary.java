package com.muyi.courage.demoForSingleton;

/**
 * @author 杨志勇
 * @date 2021-02-23 10:55
 */
public class SingletonHungary {

    // 立即加载方式==饿汉模式
    private static SingletonHungary singletonHungary =new SingletonHungary();

    private SingletonHungary(){

    }

    public static SingletonHungary getInstance(){
        // 此代码版本为立即加载
        // 此版本代码的缺点是不能有其它实例变量
        // 因为getInstance()方法没有同步
        // 所以有可能出现非线程安全问题
        return  singletonHungary;
    }

}
