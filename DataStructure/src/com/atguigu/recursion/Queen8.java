package com.atguigu.recursion;
//八皇后问题
public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后防置位置的结果，比如arr = {0,4,7,5,2,6,1,3}   数组的下标表示第几行，比如i arr[i] 是第i+1行 第i+1列
    int[] array = new int[max];
    static int count = 0;//计数，多少种解法

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(count);
    }

    //方法：放置第n个皇后
    private void check(int n){
        if (n == 8){
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){//不冲突
                //接着放下一个皇后，开始递归
                check(n + 1);
            }
            //如果冲突，则继续执行for循环array[n] = i;即把这个皇后放在该行下一列
        }
    }

    //方法：查看当我们放置第n个皇后，就去检测该皇后是否与前面已经摆放的皇后冲突
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //判断  （不需要判断是否在同一行）
            //是否在同一列 || 是否在同一条斜线上   （这里使用了斜率来表示，abs是绝对值，45度斜率为1，太妙了）
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) return false;
        }
        return true;
    }

    //方法：将皇后摆放位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
