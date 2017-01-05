package com.sh.lynn.hz.deginpattern.commandpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public class ConcreteReceiver1 extends Receiver {
    @Override
    public String doSomething() {
        return "ConcreteReceiver1 doSomething ";
    }
}
