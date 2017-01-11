package com.sh.lynn.hz.deginpattern.observerpattern;

import java.util.Vector;

/**
 * 被观察者
 * Created by hyz84 on 17/1/11.
 */

public abstract class Subject {
    //定义一个观察者集合
    private Vector<Observer> mObservers = new Vector<>();
    //添加一个观察者
    public void addObserver(Observer _observer){
        mObservers.add(_observer);
    }
    //删除一个观察者
    public void delObserver(Observer _observer){
        mObservers.remove(_observer);
    }
    //通知所有观察者
    public void notifyObservers(){
        for(Observer o:mObservers){
            o.update();
        }
    }
}
