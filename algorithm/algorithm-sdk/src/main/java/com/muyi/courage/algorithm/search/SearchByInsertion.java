package com.muyi.courage.algorithm.search;

/**
 * @author 杨志勇
 * @date 2020-10-14 20:19
 * 插值搜索(前提：有序且热不均匀数组)
 */
public class SearchByInsertion {
    public static void main(String[] args) {
        int[] a = {1,2,4,5,8,10,11,16,19};
        System.out.println(SearchByInsertion(a,0 ,a.length-1,12));
    }

    private static boolean SearchByInsertion(int a[],int low,int high,int value){

        int mid = low+(value-a[low])/(a[high]-a[low])*(high-low);

        if(a[mid]==value){
            System.out.println("find at ["+mid+"]");
            return true;

        }
        if(mid < low || mid > high){
            System.out.println("find nothing");
            return false;
        }

        if(a[mid]>value){
            return SearchByInsertion(a, low,mid-1, value);
        }

        if(a[mid]<value){
            return SearchByInsertion(a,mid+1, high, value);
        }
        return false;
    }
}
