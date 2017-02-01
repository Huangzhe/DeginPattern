package com.sh.lynn.hz.developbox.deginpattern.flyweightpattern;

/**
 * 享元模式 Flyweight Pattern 是池技术的重要实现方式
 *         使用共享对象可有效地支持大量的细粒度的对象。
 * 对象的内部状态和外部状态
 *       内部状态 intrinsic：内部状态是对象可共享出来的信息，
 *       存储在享元对象内部并且不随环境改变而改变。
 *       外部状态 extrinsic :外部状态是对象得以依赖的一个标记，是随环境改变而改变的
 * 角色：
 *      Flyweight:抽象享元角色 一个产品的抽象类，同时定义出对象的外部状态和内部状态的接口或实现。
 *      ConcreteFlyweight:具体享元角色
 *      unsharedConcreteFlyweight:不可共享的享元角色
 *      FlyweightFactory:享元工厂 构建一个池容器，同时提供从池中获取对象的方法
 *
 * 使用场景：系统中存在大量的相似对象；
 *          细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关
 *          需要缓冲池的场景
 * Created by apple on 17-2-1.
 */

public class FlyweightPattern {
}
