package com.muyi.courage.algorithm.search;

/**
 * @author 杨志勇
 * @date 2020-10-14 20:09
 * 二分查找(前提：有序数组)
 */
public class SearchByBinary {
    public static void main(String[] args) {
        int[] a = {1,2,4,5,8,10,11,16,19};
        System.out.println(SearchByBinary(a,a.length,6));
    }

    private static boolean SearchByBinary(int a[],int k,int value){
        int low = 0;
        int high = k - 1;
        int mid;
        while(low < high){
            mid = (low + high)/2;
            if(value < a[mid]){
                high = mid - 1;
            }
            if(value > a[mid]){
                low = mid + 1;
            }
            if(value == a[mid]){
                System.out.println("find at ["+mid+"]");
                return true;
            }
        }
        return false;
    }
}
