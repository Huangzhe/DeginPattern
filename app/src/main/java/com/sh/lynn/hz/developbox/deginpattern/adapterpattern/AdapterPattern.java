package com.sh.lynn.hz.developbox.deginpattern.adapterpattern;


import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * 定义：将一个累的接口变化成客户端所期待的另一种接口，
 * 从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
 * 角色：
 * Target 目标角色 该角色定义把其他类转换为我们的期望接口
 * Adaptee 源角色
 * Adapter 适配器角色 把源角色转换为目标角色，通过继承或是关联的方式。
 * 优点：
 * 适配器模式可以让两个没有任何关系的类在一起运行
 * 增加类的透明性
 * 提高了类的复用度
 * 灵活性非常好
 *
 *
 * Created by hyz84 on 17/1/8.
 */

public class AdapterPattern {
    public void excu(){
        Target target = new ConcreteTarget();
        target.request();
        Target target2 = new Adapter();
        tempBuffer.append( target.request()+"\n"+target2.request());
    }
}
