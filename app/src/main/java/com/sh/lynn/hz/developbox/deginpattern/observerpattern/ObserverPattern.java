package com.sh.lynn.hz.developbox.deginpattern.observerpattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * 也叫订阅模式(publish/subscribe)
 * 定义：定义对象间的一种一对多的依赖关系，使得每当一个对象改变状态，
 *      则所有依赖于它的对象都会得到通知并被自动更新。
 * 角色：
 * Subject 被观察者 定义被观察者必须实现的职责，他必须能动态的增加，取消观察者。
 * Oberver 观察者 观察者接收到消息后，即进行update操作，对接收到信息进行处理。
 * ConcreteSubject 具体的被观察者
 * ConcreteObserver 具体的观察者
 * 优点：
 * 1.观察者和被观察者之间是抽象的耦合，很容易扩展
 * 2.可以建立一套触发机制
 * 缺点：
 * 影响整体执行效率
 * 使用场景：
 * 关联行为场景，事件多级触发场景，跨系统的消息交换场景
 *
 * 在java中JDK已经提供了 java.util.Observable实现类和 java.util.Observer接口
 * Created by hyz84 on 17/1/11.
 */

public class ObserverPattern {

    public  void excuExample(){
        tempBuffer.delete(0,tempBuffer.length());
        //创建一个被观察者
        ConcreteSubject subject = new ConcreteSubject();
        //创建一个观察者
        Observer mOberver = new ConcreteObserver();
        subject.addObserver(mOberver);
        subject.doSomething();
    }
}
