package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1、1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        //2、因为直接对str进行操作，不方便，因此先将"1+((2+3)*4)-5" => 中缀的表达式对应的List
        //      即"1+((2+3)*4)-5" => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        //3、将ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println(suffixExpreesionList);

        /*

        //先定义一个逆波兰表达式
        //(3+4)*5-6  ->  3 4 + 5 * 6 -
        //为了方便，逆波兰表达式中的数字和符号用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";
        //思路
        //1、先将“3 4 + 5 * 6 -”放到ArrayList中
        //2、将ArrayList传递给一个方法，遍历ArrayList配合栈完成运算

        List<String> List = getListString(suffixExpression);
        int res = calculate(List);
        System.out.println(res);

         */
    }
    //?? ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =?? ArrayList [1,2,3,+,4,*,+,5,?C]
    //?????????????????????????List => ????????????List
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //?????????
        Stack<String> s1 = new Stack<String>(); // ?????
        //????????s2 ?????????????????????????pop????????????????????????????
        //????????????????????? Stack<String> ?????? List<String> s2
        //Stack<String> s2 = new Stack<String>(); // ????????????s2
        List<String> s2 = new ArrayList<String>(); // ???????????Lists2

        //????ls
        for(String item: ls) {
            //????????????????s2
            if(item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //????????????)?????????????s1?????????????????s2????????????????????????????????????
                while(!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!! ?? ( ???? s1??? ?????????
            } else {
                //??item?????????????s1????????, ??s1????????????????????s2?????????(4.1)??s1?????????????????
                //?????????????????????????????
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
                    s2.add(s1.pop());
                }
                //???????item????
                s1.push(item);
            }
        }

        //??s1????????????????????????s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2; //???????????List, ?????????????????????????????List

    }



    //将中缀表达式转成对应的List
    // s："1+((2+3)*4)-5"
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式的内容
        List<String> list = new ArrayList<String>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//多位数的拼接
        char c;//每遍历到一个字符，就放入c
        do{
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                //如果c是一个非数字，就需要加入到list
                list.add("" + c);
                i++;
            }else {
                //如果c是一个数，需要考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < s.length());
        return list;
    }

    //将一个波兰表达式，依次将数据和运算符放到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split){
            list.add(ele);
        }
        return list;
    }

    //完成对波兰表达式的运算
    public static int calculate(List<String> ls){
        //创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item: ls){
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")){//匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                //pop出两个数并运算,再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")){
                    result = num2 + num1;
                }else if (item.equals("-")){
                    result = num2 - num1;
                }else if (item.equals("*")){
                    result = num2 * num1;
                }else if (item.equals("/")){
                    result = num2 / num1;
                }else {
                    throw new RuntimeException("运算有误");
                }
                stack.push(""+result);
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//???????? Operation ??????????????? ??????????
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //???????????????????????????
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("????????????" + operation);
                break;
        }
        return result;
    }

}
