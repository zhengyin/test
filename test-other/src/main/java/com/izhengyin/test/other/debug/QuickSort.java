package com.izhengyin.test.other.debug;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-11-23 14:29
 */

import com.alibaba.fastjson.JSON;

/**
 * 快速排序演示
 * @author Lvan
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 7, 3, 1, 6, 9,0, 2, 4};
        //4,1,2,3,1,5,9,6,7
        //4,1,2,3,1,0,5,9,6,7
        System.out.println(JSON.toJSONString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));
    }

    /**
     * @param arr        待排序列
     * @param leftIndex  待排序列起始位置
     * @param rightIndex 待排序列结束位置
     */
    private static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int left = leftIndex;
        int right = rightIndex;
        //待排序的第一个元素作为基准值
        int key = arr[left];

        //从左右两边交替扫描，直到left = right
        while (left < right) {
            while (right > left && arr[right] >= key) {
                //从右往左扫描，找到第一个比基准值小的元素
                right--;
            }

            //找到这种元素将arr[right]放入arr[left]中
            arr[left] = arr[right];

            while (left < right && arr[left] <= key) {
                //从左往右扫描，找到第一个比基准值大的元素
                left++;
            }

            //找到这种元素将arr[left]放入arr[right]中
            arr[right] = arr[left];
            System.out.println(JSON.toJSONString(arr) +" ("+left+","+right+")");
        }
        //基准值归位
        arr[left] = key;
        System.out.println(JSON.toJSONString(arr) +" ("+left+","+right+")");
        //对基准值左边的元素进行递归排序
        quickSort(arr, leftIndex, left - 1);
        //对基准值右边的元素进行递归排序。
        quickSort(arr, right + 1, rightIndex);
    }
}