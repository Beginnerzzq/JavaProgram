package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//基数排序（桶排序）  使用空间换时间的经典算法
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0,8000000)数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是" + date1Str);

        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是" + date2Str);

    }

    public static void radixSort(int[] arr) {
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1、二维数组包含10个一维数组
        //2、为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为 arr.length
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];
        
        //得到数组中最大数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        int maxLength = (max + "").length();//加上空字符串，使 max 变成字符串，长度即为位数

        //针对每个元素的个、十、百、千...位进行排序处理
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / (int)Math.pow(10,i) % 10;
                //将 arr[j] 放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCounts[k] != 0)
                    for (int m = 0; m < bucketElementCounts[k]; m++) {
                        arr[index] = bucket[k][m];
                        index++;
                    }
                //每一轮处理后，需要将每个桶的数据数清零
                bucketElementCounts[k] = 0;
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
