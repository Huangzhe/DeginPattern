package com.sh.lynn.hz.developbox.deginpattern.mementopattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * 备忘录模式：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 *            这样以后就可以将该对象恢复到原先保存状态。通俗的说就是一个对象的备份模式，
 *            提供一种程序数据的备份方法。
 * 备忘录模式的角色：
 *          Originator:发起人角色 记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。
 *          Memento：备忘录角色 负责存储Originator发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。
 *          Caretaker:备忘录管理员角色  对备忘录进行管理、保存和提供备忘录
 *使用场景：
 *      1.需要保存和恢复数据的相关场景
 *      2.提供一个可回滚的操作
 *      3.需要监控的副本场景中
 *      4.数据库连接的事物
 * 注意事项：
 *     备忘录的生命周期，备忘录的性能
 *
 * Created by apple on 17-1-31.
 */

public class MementoPattern {
    public void excuExample(){
        tempBuffer.delete(0,tempBuffer.length());
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("First State!\n");
        caretaker.setMemento(originator.createMemento());
        originator.setState("Second State!\n");
        tempBuffer.append("恢复 state\n");
        originator.restoreMemento(caretaker.getMemento());
       // String state = originator.getState();
       // tempBuffer.append("current state:"+state);
    }
}
