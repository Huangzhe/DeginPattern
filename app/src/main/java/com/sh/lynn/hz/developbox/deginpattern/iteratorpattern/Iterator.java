package com.sh.lynn.hz.developbox.deginpattern.iteratorpattern;

/**
 * Created by hyz84 on 17/1/10.
 */

public interface Iterator {
    //遍历下一个元素
    public Object next();
    //判断是否已经遍历到尾部
    public boolean hasNext();
    //删除当前指向的元素
    public boolean remove();
}
