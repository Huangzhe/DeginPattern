package com.sh.lynn.hz.deginpattern.decoratorpattern;

/**
 * Created by hyz84 on 17/1/5.
 */

public class ConcreteDecorator1 extends Decorator {
    //定义被修饰者
    public ConcreteDecorator1(Component _component) {
        super(_component);
    }
    //定义自己的方法
    private String method1(){
       return "methdo1 修饰";
    }
    public String operate(){
      return   method1()+super.operate();

    }
}
