package com.sh.lynn.hz.developbox.deginpattern.statepattern;

/**
 * Created by apple on 17-2-1.
 */

public class MyContext {
    //定义状态
    public final static State STATE1 = new ConcreteState1();
    public final static State STATE2 = new ConcreteState2();
    //当前状态
    public State currentState;
    //获取当前状态
    public State getCurrentState(){
        return currentState;
    }
    //设置当前状态
    public void setCurrentState(State _currentState) {
        this.currentState = _currentState;
        //切换状态
        this.currentState.setContext(this);
    }
    //行为委托
    public void handle1(){
        this.currentState.handle1();
    }
    public void handle2(){
        this.currentState.handle2();
    }
}
