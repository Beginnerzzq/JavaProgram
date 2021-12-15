package com.atguigu.stack;

public class Calculator {
    public static void main(String[] args) {
        //根据老师的思路，完成表达式的运算
        String expression = "30+2*6-10";
        //创建两个栈，数栈，符号栈
        CalculatorStack numStack = new CalculatorStack(10);
        CalculatorStack operStack = new CalculatorStack(10);
        //定义index指针,用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = ' ';
        int result = 0;
        char ch = ' ';//将每次扫描得到char保存到ch
        String keepNumber = "";//拼接字符串，用于拼接多位数
        //开始while循环扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){
                //如果是运算符
                //判断栈是否为空
                if (operStack.isEmpty()){
                    //如果为空直接入栈
                    operStack.push(ch);
                }else {
                    //如果不为空，则比较优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.topData())){
                        //如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，从符号栈中pop出一个符号，进行运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1,num2,oper);
                        //将运算结果入数栈
                        numStack.push(result);
                        //将index指向的符号入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前符号的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }
            }else {
                //如果是数，则入数栈
                //numStack.push(ch - 48);//ch是字符类型，根据ASCII码表，二进制转化为十进制要多出48
                //分析思路：
                //1、当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2、在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3、因此我们需要定义一个变量字符串（keepNumber），用于拼接

                //处理多位数
                keepNumber += ch;

                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNumber));
                }else {
                    //判断下一个字符是否是数字,如果是数字则继续扫描
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符，则入栈keepNumber
                        numStack.push(Integer.parseInt(keepNumber));
                        //Important!!!,清空keepNumber
                        keepNumber = "";
                    }
                }
            }
            index++;
            //判断是否到表达式最后
            if (index >= expression.length()){
                System.out.println(index);
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并计算
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字[result]
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1,num2,oper);
            //将运算结果入数栈
            numStack.push(result);
        }
        System.out.printf("表达式%s = %d\n",expression,numStack.pop());
    }
}

//先创建一个栈
class CalculatorStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在数组里
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public CalculatorStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //返回栈顶数据,但不出栈
    public int topData(){
        return stack[top];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈-push
    public void push(int value){
        //先判断是否栈满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop，将栈顶的数据返回
    public int pop(){
        //先判断是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈[遍历栈]，遍历时，需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级，优先级由程序员决定，优先级用数字表示
    //数字越大则优先级越高
    public int priority(int oper){
        //假定目前表达式只有加减乘除
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是否为运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int result = 0;//计算结果
        switch (oper){
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:break;
        }
        return result;
    }
}

