package com.sh.lynn.hz.developbox.deginpattern.commandpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public abstract class Command {
    //每个命令类都必须有个执行命令的方法
    public abstract String execute();
}
