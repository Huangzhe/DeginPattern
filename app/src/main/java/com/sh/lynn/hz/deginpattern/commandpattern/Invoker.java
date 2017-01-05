package com.sh.lynn.hz.deginpattern.commandpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public class Invoker {
    private Command mCommand;
    //接收命令
    public void setCommand(Command _command){
        this.mCommand=_command;
    }

    public String action(){
       return this.mCommand.execute();
    }
}
