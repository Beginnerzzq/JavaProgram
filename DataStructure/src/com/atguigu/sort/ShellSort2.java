package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//希尔排序（缩小增量排序） 移位法
public class ShellSort2 {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
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
        //增量gap,并逐步缩小gap
        for (int gap = arr.length/2; gap > 0; gap /= 2){
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];

                if (arr[j-gap] > arr[j])
                    while (j-gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }

                arr[j] = temp;
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
