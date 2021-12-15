package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//选择排序
public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1,1,-1,90,123};
        //测试选择排序的速度(80000个数据)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);//生成一个[0,8000000)数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + date1Str);

        selectSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static  void selectSort(int[] arr){
        //时间复杂度 O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;//最小值索引(下标)
            int min = arr[i];//假设最小值是arr[0]
            for (int j = minIndex + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    //说明假设的不是最小值,重置最小值
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
            //System.out.println(Arrays.toString(arr));
        }
    }
}
