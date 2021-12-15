package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//冒泡排序(优化)
public class BubbleSort2 {
    public static void main(String[] args) {
        //int arr[] = {3,9,-1,10,20};
        //测试冒泡排序的速度(80000个数据)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);//生成一个[0,8000000)数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + date1Str);

        bubbleSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;//标识变量
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i+1]){
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
            if (!flag){
                break;//一次交换都没有，说明排序好了，无需继续排序
            }else {
                flag = false;//重置flag
            }
        }
    }
}
