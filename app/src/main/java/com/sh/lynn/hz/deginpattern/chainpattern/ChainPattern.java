package com.sh.lynn.hz.deginpattern.chainpattern;

/**
 * 责任链模式：使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。
 * 将这些对象连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。
 * Created by hyz84 on 16/12/28.
 */

public class ChainPattern {

    public String doSomething(){
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        Response response1 = handler1.handleMessage(new Request(Level.HIGHT,"hight request"));
        Response response2 = handler1.handleMessage(new Request(Level.LOW,"low request"));
        Response response3 = handler1.handleMessage(new Request(Level.MID,"mid request"));
       return response1.getResult()+"\n"+response2.getResult()+"\n"+response3.getResult();
    }
}
