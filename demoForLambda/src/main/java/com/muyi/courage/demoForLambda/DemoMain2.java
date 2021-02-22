package com.muyi.courage.demoForLambda;

/**
 * @author 杨志勇
 * @date 2021-02-22 14:08
 */
public class DemoMain2 {
    /**一个参数有返回值*/
    @FunctionalInterface
    public interface ReturnOneParam {
        int method(int a);
    }

    public static void main(String[] args) {

        ReturnOneParam returnOneParam = a -> doubleNum(a);
        System.out.println(returnOneParam.method(1));

        ReturnOneParam returnOneParam1 = DemoMain2::doubleNum;
        System.out.println(returnOneParam1.method(2));

        DemoMain2 demoMain2 = new DemoMain2();
        ReturnOneParam returnOneParam2 = demoMain2::addTwo;
        System.out.println(returnOneParam2.method(3));

    }
    /**
     * 要求
     * 1.参数数量和类型要与接口中定义的一致
     * 2.返回值类型要与接口中定义的一致
     */
    public static int doubleNum(int a) {
        return a * 2;
    }

    public int addTwo(int a) {
        return a + 2;
    }
}
