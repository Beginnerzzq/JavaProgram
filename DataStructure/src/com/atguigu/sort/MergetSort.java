package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//归并排序
public class MergetSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + date1Str);

        mergeSort(arr,0, arr.length-1,temp);
//        System.out.println(Arrays.toString(arr));

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + date2Str);

    }

    //分解+合并
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并（8、4在栈顶，最先合并）
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i。左边有序序列的初始索引
        int j = mid + 1;//初始化j。右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //（一）
        //先把左右两边（有序）的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                //如果左边有序序列的当前元素，小于等于右边有序序列的当前元素
                //即将左边的当前元素，拷贝到temp数组。然后t++ i++
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                //反之，将右边。。
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //（二）
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {
            //左边的有序序列元素还有剩余
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            //左边的有序序列元素还有剩余
            temp[t] = arr[j];
            t++;
            j++;
        }

        //（三）
        //将temp数组的元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
