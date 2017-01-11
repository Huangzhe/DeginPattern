package com.sh.lynn.hz.deginpattern.iteratorpattern;

import static com.sh.lynn.hz.deginpattern.Utils.tempBuffer;

/**
 * 定义：提供一个方法访问一个容器对象中各个元素，而又不暴露该对象内部的细节
 * 角色：
 * Iterator：抽象迭代器
 * ConcreteIterator 具体迭代器
 * Aggregate 抽象容器
 * ConcreteAggregate 具体迭代器
 * Created by hyz84 on 17/1/10.
 */

public class IteratorPattern {
    public void doSomething(){
        Aggreate aggreate = new ConcreteAggregate();
        aggreate.add("abc");
        aggreate.add("xyz");
        Iterator iterator  = aggreate.iterator();
        String result="";
        while (iterator.hasNext()){
            result+=iterator.next()+"\n";
        }
        tempBuffer.append( result);
    }
}
