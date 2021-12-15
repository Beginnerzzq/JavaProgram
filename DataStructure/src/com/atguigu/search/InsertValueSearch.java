package com.atguigu.search;

//插值查找
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr,0, arr.length-1,2);
        System.out.println(index);
    }

    public static int insertValueSearch(int[] arr, int low, int high, int findVal) {
        if (low > high || findVal < arr[0] || findVal > arr[arr.length - 1])
            return -1;
        int mid = low + (high - low) * (findVal - arr[low]) / (arr[high] - arr[low]);
        if (findVal == arr[mid]) return mid;
        if (findVal < arr[mid])
            return insertValueSearch(arr, low, mid - 1, findVal);
        if (findVal > arr[mid])
            return insertValueSearch(arr, mid + 1, high, findVal);
        return -1;
    }
}
