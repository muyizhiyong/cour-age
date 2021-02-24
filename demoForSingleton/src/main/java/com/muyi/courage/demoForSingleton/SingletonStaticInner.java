package com.muyi.courage.demoForSingleton;

/**
 * @author 杨志勇
 * @date 2021-02-23 14:49
 */
public class SingletonStaticInner {

    private SingletonStaticInner(){

    }

    private static class SingletnInner{
        private static SingletonStaticInner singletonStaticInner = new SingletonStaticInner();
    }

    public static SingletonStaticInner getInstance(){
        try{
            Thread.sleep(1000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return SingletnInner.singletonStaticInner;
    }

}
