package com.sh.lynn.hz.developbox.deginpattern.visitorpattern;

/**
 * 抽象访问者
 * Created by apple on 17-1-31.
 */

public interface IVisitor {
    public void visit(CocreteElement1 element);
    public void visit(CocreteElement2 element);
}
