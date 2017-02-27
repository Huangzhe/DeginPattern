package com.sh.lynn.hz.developbox.deginpattern.mementopattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * 发起人角色
 * Created by apple on 17-1-31.
 */

public class Originator {
    //内部状态
    private String state="1";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        tempBuffer.append("current state:"+state);
    }

    /**
     * 创建一个备忘录
     * @return
     */
    public Memento createMemento(){
        return new Memento(state);
    }

    /**
     * 恢复备忘录
     * @param _memento
     */
    public void restoreMemento(Memento _memento){
        this.setState(_memento.getState());
    }
}
