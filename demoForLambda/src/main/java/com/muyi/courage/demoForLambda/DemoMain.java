package com.muyi.courage.demoForLambda;

/**
 * @author 杨志勇
 * @date 2021-02-22 11:09
 */
public class DemoMain {

    /**无参无返回值*/
    @FunctionalInterface
    public interface NoReturnNoParam {
        void method();
    }

    /**一个参数无返回*/
    @FunctionalInterface
    public interface NoReturnOneParam {
        void method(int a);
    }

    /**多参数无返回*/
    @FunctionalInterface
    public interface NoReturnMultiParam {
        void method(int a, int b);
    }

    /*** 无参有返回*/
    @FunctionalInterface
    public interface ReturnNoParam {
        int method();
    }

    /**一个参数有返回值*/
    @FunctionalInterface
    public interface ReturnOneParam {
        int method(int a);
    }

    /**多个参数有返回值*/
    @FunctionalInterface
    public interface ReturnMultiParam {
        int method(int a, int b);
    }

    public static void main(String[] args) {
        System.out.println("start...");

        //无参无返回
        NoReturnNoParam  noReturnNoParam =() -> {
            System.out.println("NoReturnNoParam");
        };
        noReturnNoParam.method();

        //单参无返回
        NoReturnOneParam noReturnOneParam =(int a) -> {
            System.out.println("NoReturnOneParam");
            System.out.println("receive: "+a);
        };
        noReturnOneParam.method(12);

        //多参无返回
        NoReturnMultiParam noReturnMultiParam =(int a ,int b)-> {
            System.out.println("NoReturnMultiParam");
            System.out.println("receive p1: "+a);
            System.out.println("receive p2: "+b);
        };
        noReturnMultiParam.method(1,2);

        //无参有返回
        ReturnNoParam returnNoParam = ()->{
            System.out.println("ReturnNoParam");
            return  1;
        };
        int ret = returnNoParam.method();
        System.out.println("ret: " + ret);

        //单参有返回
        ReturnOneParam returnOneParam = (int a) -> {
            System.out.println("ReturnOneParam");
            System.out.println("receive: "+a);
            return a;
        };
        int ret1= returnOneParam.method(1);
        System.out.println("ret1: "+ret1);

        //多参有返回
        ReturnMultiParam returnMultiParam =(int a ,int b)->{
            System.out.println("returnMultiParam");
            System.out.println("receive p1: "+a);
            System.out.println("receive p2: "+b);
            return 1;
        };
        int ret2= returnMultiParam.method(1,2);
        System.out.println("ret2: "+ret2);

        //代码简化
        //1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
        NoReturnMultiParam noReturnMultiParam1 = (a,b) -> {
            System.out.println("简化参数类型");
        };
        noReturnMultiParam1.method(1,2);

        //2.简化参数小括号，如果只有一个参数可以省略参数小括号
        NoReturnOneParam noReturnOneParam1 = a -> {
            System.out.println("简化参数小括号");
        };
        noReturnOneParam1.method(1);

        //3.简化方法体大括号，如果方法只有一条语句，可省略方法体大括号
        NoReturnOneParam noReturnOneParam2 = a -> System.out.println("简化方法体大括号");
        noReturnOneParam2.method(1);

        ReturnOneParam returnOneParam1 = a -> a+2;
        int ret3 = returnOneParam1.method(1);
        System.out.println("ret3: "+ret3);
    }
}
