package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//希尔排序（缩小增量排序） 交换法
public class ShellSort1 {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);//生成一个[0,8000000)数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + date1Str);

        shellSort(arr);
//        System.out.println(Arrays.toString(arr));

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static void shellSort(int[] arr) {
        int temp = 0;
        //增量gap,并逐步缩小gap
        for (int gap = arr.length/2; gap > 0; gap /= 2){
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
