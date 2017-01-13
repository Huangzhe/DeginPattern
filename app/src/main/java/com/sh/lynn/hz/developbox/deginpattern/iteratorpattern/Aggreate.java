package com.sh.lynn.hz.developbox.deginpattern.iteratorpattern;

/**
 * Created by hyz84 on 17/1/10.
 */

public interface Aggreate {
    //添加元素
    public void add(Object object);
    //减少元素
    public void remove(Object object);
    //由迭代器来遍历所有元素
    public Iterator iterator();
}
