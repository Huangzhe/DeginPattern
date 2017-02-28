package com.sh.lynn.hz.developbox.deginpattern.compositepattern;

/**
 * Created by hyz84 on 17/1/10.
 */

public abstract class Component {
    //个体和整体都具有的共享
    public String doSomething(){
        //业务逻辑
        return "Component";
    }
}
