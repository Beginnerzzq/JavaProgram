package com.atguigu.linkedList;

public class Josephu {
    public static void main(String[] args) {
        //构建环形链表，并遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点，构成一个环形链表
    public void addBoy(int nums) {
        //nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getId());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算小孩出圈的顺序
     * startId 表示从第几个小孩开始数数
     * countNum 表示数几下
     * nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startId, int countNum, int nums) {
        //先对数据进行一个校验
        if (first == null || startId < 1 || startId > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针helper，事先应指向环形链表的最后的节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) break;//说明helper指向最后小孩节点
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动startId - 1次
        for (int j = 0; j < startId - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动countNum - 1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true){
            if (first == helper) break;//说明圈中只有一个节点
            //让first和helper指针同时的移动countNum - 1次
            for (int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getId());
            //这时first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后剩下的小孩是%d\n",first.getId());
    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int id;//编号
    private Boy next;//指向下一个节点，默认null

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}