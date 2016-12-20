package com.sh.lynn.hz.deginpattern.mediator;

/**
 * Created by hyz84 on 16/12/20.
 */

public class ConcreteMediator extends Mediator {
    @Override
    public void doSomething1() {
        super.mColleague1.selfMethod1();
        super.mColleague2.selfMethod2();
    }

    @Override
    public void doSomething2() {
        super.mColleague1.selfMethod1();
        super.mColleague2.selfMethod2();
    }
}
