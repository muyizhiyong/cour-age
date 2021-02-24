package com.muyi.courage.demoForSingleton;

/**
 * @author 杨志勇
 * @date 2021-02-23 15:04
 */
public class SingletonStaticBlock {
    private static SingletonStaticBlock singletonStaticBlock;

    static {
        singletonStaticBlock = new SingletonStaticBlock();
    }

    public static SingletonStaticBlock getInstance(){
        return singletonStaticBlock;
    }
}
