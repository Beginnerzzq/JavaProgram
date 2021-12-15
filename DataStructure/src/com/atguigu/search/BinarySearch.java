package com.atguigu.search;
//二分查找
public class BinarySearch {
    static int arr[] = {1, 4, 5, 7, 8, 9, 10, 12, 15, 22, 23, 27, 32, 35, 39};
    static int x = 22;

    static int binSearch(int low, int high) {
        if (low > high) return -1;

        int mid = (high + low) / 2;

        if (x == arr[mid]) return mid;
        if (x < arr[mid]) return binSearch(low, mid - 1);
        if (x > arr[mid]) return binSearch(mid + 1, high);
        return -1;
    }

    public static void main(String[] args) {
        int index = binSearch(0, arr.length - 1);
        System.out.println(index);
    }
}