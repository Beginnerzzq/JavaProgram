package com.atguigu.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建一个哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add：  添加雇员");
            System.out.println("del：  删除雇员");
            System.out.println("list： 显示雇员");
            System.out.println("find:  查找雇员");
            System.out.println("exit： 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab  管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListsArray;
    private int size;//表示有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListsArray
        empLinkedListsArray = new EmpLinkedList[size];
        //注意一定要初始化每个链表，每个元素都要创建
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListsArray[empLinkedListNO].add(emp);
    }

    //删除雇员
    public void delete(int id){
        int empLinkedListNO = hashFun(id);
        empLinkedListsArray[empLinkedListNO].delete(id);
    }

    //遍历所有的链表，遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i].list(i + 1);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListsArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    //编写散列函数，使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head;

    //添加雇员到链表
    //说明：
    //1、假定，当添加雇员时，id是自增长，即id的分配总是从小到大
    //   因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) break;//说明链表到最后
            curEmp = curEmp.next;//后移
        }
        //退出时直接将emp加入链表
        curEmp.next = emp;
    }

    //从链表中删除雇员
    public void delete(int id){
        Emp curEmp = head;
        boolean flag = false; // 标志是否找到待删除节点的前一个节点
        if(head.id == id){
            head = head.next;//头节点为待删节点，则删除头节点，头节点后移
            System.out.println("已删除该雇员");
            return;
        }
        while (true){
            if (curEmp.next == null){
                //遍历到链表最后
                break;
            }
            if (curEmp.next.id == id){
                //说明找到待删除节点的前一个节点
                flag = true;
                break;
            }
            curEmp = curEmp.next;
        }
        if (flag){
            curEmp.next = curEmp.next.next;
            System.out.println("已删除该雇员");
        }else {
            System.out.printf("要删除的 id = %d 雇员不存在\n", id);
        }
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表为空");
            return;
        }
        System.out.println("第" + no + "条链表的信息为");
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) break;//说明curEmp已经是最后节点
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp，如果没有找到，就返回null
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) break;//说明找到
            if (curEmp.next == null) {
                curEmp = null;//说明遍历当前链表没有找到该雇员
                break;
            }
            curEmp = curEmp.next;//后移
        }
        return curEmp;
    }
}
