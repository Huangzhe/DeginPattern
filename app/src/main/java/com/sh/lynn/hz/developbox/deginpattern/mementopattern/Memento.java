package com.sh.lynn.hz.developbox.deginpattern.mementopattern;

/**
 * 备忘录角色
 * Created by apple on 17-1-31.
 */

public class Memento {
    private String state="";
    public Memento(String _state){
        this.state=_state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
