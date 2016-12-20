package com.sh.lynn.hz.deginpattern.mediator;

/**
 * Created by hyz84 on 16/12/20.
 */

public abstract class Mediator {
    //定义同事类
    protected ConcreteColleague1 mColleague1;
    protected ConcreteColleague2 mColleague2;

    public ConcreteColleague1 getColleague1() {
        return mColleague1;
    }

    public void setColleague1(ConcreteColleague1 colleague1) {
        mColleague1 = colleague1;
    }

    public ConcreteColleague2 getColleague2() {
        return mColleague2;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        mColleague2 = colleague2;
    }
    //中介者模式的业务逻辑
    public abstract void doSomething1();
    public abstract void doSomething2();
}
