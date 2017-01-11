package com.sh.lynn.hz.deginpattern.observerpattern;

import static com.sh.lynn.hz.deginpattern.Utils.tempBuffer;

/**
 * Created by hyz84 on 17/1/11.
 */

public class ConcreteSubject extends Subject {
    public void doSomething(){
        super.notifyObservers();
        tempBuffer.append("ConcreteSubject doSomething！ \n");
    }
}
