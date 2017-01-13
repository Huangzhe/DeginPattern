package com.sh.lynn.hz.developbox.deginpattern.iteratorpattern;

import java.util.Vector;

/**
 * Created by hyz84 on 17/1/10.
 */

public class ConcreteAggregate implements Aggreate {
    //容纳对象的容器
    private Vector mVector = new Vector();
    @Override
    public void add(Object object) {
        mVector.add(object);
    }

    @Override
    public void remove(Object object) {
        mVector.remove(object);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(mVector);
    }
}
