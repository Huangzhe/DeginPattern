package com.sh.lynn.hz.developbox.deginpattern.mementopattern;

/**
 * 备忘录管理员角色
 * Created by apple on 17-1-31.
 */

public class Caretaker {
    private Memento memento;
    public Memento getMemento(){
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
