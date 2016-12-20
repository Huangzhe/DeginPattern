package com.sh.lynn.hz.deginpattern.mediator;

/**
 * Created by hyz84 on 16/12/20.
 */

public class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator _mediator) {
        super(_mediator);
    }
//自有方法
    public void selfMethod1(){
        //处理自身业务逻辑
    }
    //依赖方法 dep-method
    public void depMethod1(){
        //处理自己的业务逻辑
        //自己不能处理的业务逻辑，委托中介者处理
        super.mMediator.doSomething1();
    }
}
