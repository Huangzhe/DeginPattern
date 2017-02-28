package com.sh.lynn.hz.developbox.deginpattern.statepattern;

/**
 * 抽象环境角色
 * Created by apple on 17-2-1.
 */

public abstract class State {
    //定义一个环境角色，提供子类访问
    protected  MyContext context;
    //设置环境角色
    public void setContext(MyContext _context){
        this.context=_context;
    }
    //行为1
    public abstract void handle1();
    //行为2
    public abstract  void  handle2();
}
