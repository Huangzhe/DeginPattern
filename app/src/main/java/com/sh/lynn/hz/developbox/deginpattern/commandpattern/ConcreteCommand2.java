package com.sh.lynn.hz.developbox.deginpattern.commandpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public class ConcreteCommand2 extends Command {
    //对哪个Receiver类进行命令处理
    private Receiver receiver;

    public ConcreteCommand2(Receiver _receiver){
        receiver = _receiver;
    }

    @Override
    public String execute() {
        return  this.receiver.doSomething();
    }
}
