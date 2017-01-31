package com.sh.lynn.hz.developbox.deginpattern.visitorpattern;

/**
 * 抽象元素
 * Created by apple on 17-1-31.
 */

public abstract class Element {
    public abstract void doSomething();
    public abstract void accept(IVisitor _visitor);
}
