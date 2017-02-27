package com.sh.lynn.hz.developbox.deginpattern.visitorpattern;

/**
 * Created by apple on 17-1-31.
 */

public class Visitor implements IVisitor {
    /**
     * 访问element1
     * @param element
     */
    @Override
    public void visit(CocreteElement1 element) {
        element.doSomething();
    }

    /**
     * 访问Element2
     * @param element
     */
    @Override
    public void visit(CocreteElement2 element) {
        element.doSomething();
    }
}
