package com.izhengyin.test.other.debug;

import com.alibaba.fastjson.JSON;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-11-23 15:21
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 3, 4,2,3,2,1,3,6,8,9,7,10};
       quick_sort(arr,0,arr.length - 1);
    }

    public static void quick_sort(int[] arr , int lIndex , int rIndex){
        if(lIndex >= rIndex){
            return;
        }
        int p = partition(arr, lIndex, rIndex);
        System.out.println(p+" , "+JSON.toJSONString(arr));
        quick_sort(arr, lIndex, p-1);
        quick_sort(arr, p+1, rIndex);
    }
    // 2,1,3,1,4,5,7
    public static int partition(int[] arr , int lIndex , int rIndex){
        int l = lIndex;
        int r = rIndex;
        int baseValue = arr[l];
        while (l < r){
            //从右往左查找，一直找到比基准值小的，进行单向复制
            while (l < r && baseValue <= arr[r]){
                r --;
            }
            arr[l] = arr[r];
            //从左往右查找,一直找到比基准值大的，进行单向复制
            while (l < r && baseValue >= arr[l]){
                l ++;
            }
            arr[r] = arr[l];
            //两次单向复制以后，完成一次交换，开始下一个循环
        }
        //复原
        arr[l] = baseValue;
        return l;
    }
}
