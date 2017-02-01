package com.sh.lynn.hz.developbox.deginpattern.bridgepattern;

/**
 * 桥梁模式 也叫桥接模式 将抽象和实现解耦，使得两者可以独立的变化
 * 角色：
 *      Abstraction:抽象化角色，定义出该角色的行为，同时保存一个对实现化角色的应用，一般抽象类。
 *      Implementor:实现化角色 接口或者抽象类，定义角色必须的行为和属性
 *      RefinedAbstraction:修正抽象化角色 它引用实现化角色对抽象化角色进行修正。
 *      ConcreteImplementor:具体实现化角色
 * Created by apple on 17-2-1.
 */

public class BridgePattern {

    public void excuExample(){
        Implementor implementor = new ConcreteImplementor1();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.request();
    }
}
