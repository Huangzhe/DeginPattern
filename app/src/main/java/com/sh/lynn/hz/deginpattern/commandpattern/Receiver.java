package com.sh.lynn.hz.deginpattern.commandpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public abstract class Receiver {
    //抽象接收者，定义每个接收者都必须完成的业务
    public abstract String doSomething();
}
