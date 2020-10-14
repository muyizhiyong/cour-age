package com.muyi.courage.algorithm.search;

/**
 * @author 杨志勇
 * @date 2020-10-14 20:05
 * 顺序查找
 */
public class SearchBySequence {
    public static void main(String[] args) {
        int[] a = {8,2,4,5,3,10,11,6,9};
        System.out.println(SequenceSearch(a,a.length,18));
    }

    private static boolean SequenceSearch(int a[],int k,int value){
        for( int i = 0 ; i<k; i++){
            if( value == a[i]){
                return true;
            }
        }
        return false;
    }

}
