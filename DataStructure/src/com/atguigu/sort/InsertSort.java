package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//插入排序
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1};
        //测试插入排序的速度(80000个数据)
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (int)(Math.random()*8000000);//生成一个[0,8000000)数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + date1Str);

        insertSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //定义待插入的数
            int insertVal = arr[i + 1];
            int insertIndex = i;//待插入数前面的数的下标

            //给insertVal找到插入位置
            //说明
            //1.insertIndex >= 0  保证给insertVal找插入位置，不越界
            //2.insertVal < arr[insertIndex]   待插入的数，还没有找到插入位置
            //3.就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
            //这里判断以下是否需要赋值，优化算法
            if (insertIndex != i) arr[insertIndex + 1] = insertVal;
            //System.out.println(Arrays.toString(arr));
        }
    }
}
