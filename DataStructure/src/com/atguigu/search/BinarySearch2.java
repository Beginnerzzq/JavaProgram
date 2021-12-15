package com.atguigu.search;

//思考题  当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的22

import java.util.ArrayList;
import java.util.List;

/**
 * 思路分析
 * 1、在找到mid索引值，不要立刻返回
 * 2、向mid索引值的左边扫描，将所有满足22的元素的下标加入结合ArrayList
 * 3、向mid索引值的右边扫描，将所有满足22的元素的下标加入结合ArrayList
 * 4、将ArrayList返回
 */
public class BinarySearch2 {
    static int arr[] = {1, 4, 5, 7, 8, 9, 10, 12, 15, 22, 22, 22, 23, 27, 32, 35, 39};
    static int x = 22;

    static List<Integer> binSearch(int low, int high) {
        if (low > high) return new ArrayList<Integer>();

        int mid = (high + low) / 2;

        if (x == arr[mid]) {
            List<Integer> indexList = new ArrayList<Integer>();
            indexList.add(mid);
            //向mid索引值的左边扫描，将所有满足22的元素的下标加入结合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != x) break;
                indexList.add(temp--);
            }
            //向mid索引值的you边扫描，将所有满足22的元素的下标加入结合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != x) break;
                indexList.add(temp++);
            }
            return indexList;
        }
        if (x < arr[mid]) return binSearch(low, mid - 1);
        if (x > arr[mid]) return binSearch(mid + 1, high);
        return new ArrayList<Integer>();
    }

    public static void main(String[] args) {
        List index = binSearch(0, arr.length - 1);
        System.out.println(index);
    }
}