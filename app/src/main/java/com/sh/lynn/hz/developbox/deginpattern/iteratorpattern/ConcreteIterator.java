package com.sh.lynn.hz.developbox.deginpattern.iteratorpattern;

import java.util.Vector;

/**
 * Created by hyz84 on 17/1/10.
 */

public class ConcreteIterator implements Iterator {

    private Vector mVector = new Vector();
    //定义当前游标
    public int cursor=0;

    public ConcreteIterator(Vector _vector){
        mVector=_vector;
    }

    @Override
    public Object next() {
        Object result = null;
        if(hasNext()){
            result=mVector.get(cursor++);
        }

        return result;
    }

    @Override
    public boolean hasNext() {
        if(cursor==mVector.size()){
            return false;
        }
        return true;
    }

    @Override
    public boolean remove() {
        mVector.remove(cursor);
        return true;
    }
}
