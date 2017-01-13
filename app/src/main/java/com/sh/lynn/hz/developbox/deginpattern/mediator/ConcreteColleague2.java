package com.sh.lynn.hz.developbox.deginpattern.mediator;

/**
 * Created by hyz84 on 16/12/20.
 */

public class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator _mediator) {
        super(_mediator);
    }
//自有方法
    public void selfMethod2(){
        //处理自身业务逻辑
    }
    //依赖方法 dep-method
    public void depMethod2(){
        //处理自己的业务逻辑
        //自己不能处理的业务逻辑，委托中介者处理
        super.mMediator.doSomething2();
    }
}
