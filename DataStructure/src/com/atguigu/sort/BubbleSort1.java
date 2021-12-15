package com.atguigu.sort;

import java.util.Arrays;

//冒泡排序
public class BubbleSort1 {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};
        int temp = 0;
        //时间复杂度为 O(n^2)
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i+1]){
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
            System.out.println();
        }
    }
}
