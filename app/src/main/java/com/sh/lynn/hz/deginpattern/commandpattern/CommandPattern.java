package com.sh.lynn.hz.deginpattern.commandpattern;

import static com.sh.lynn.hz.deginpattern.Utils.tempBuffer;

/**
 * 命令模式：将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，
 * 对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
 * 命令模式三个角色：
 * Receive 接收者角色
 * Command 命令角色
 * Invoker 调用者角色
 * Created by hyz84 on 16/12/28.
 */

public class CommandPattern {


    public void doSomething(){
        //声明调用者Invoker
        Invoker invoker  = new Invoker();
        //定义接收者
        Receiver receiver1 = new ConcreteReceiver1();
        //定义一个发送给接收者的命令
        Command command1 = new ConcreteCommand1(receiver1);
        invoker.setCommand(command1);
       String result1 = invoker.action();
        //定义接收者
        Receiver receiver2 = new ConcreteReceiver2();
        //定义一个发送给接收者的命令
        Command command2 = new ConcreteCommand2(receiver2);
        invoker.setCommand(command2);
        String result2 =  invoker.action();
        tempBuffer.append( result1+"\n"+result2);
    }
}
