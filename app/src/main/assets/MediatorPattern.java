/**
 * 定义：用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示相互作用，
 *       从而使其耦合松散，而且可以独立地改变它们之间的交互。
 *中介者模式的组成部分：
 * 1.Mediator 抽象中介者角色 定义统一的接口，用于各同事角色之间的通信。
 * 2.Concrete Mediator 具体中介者角色  通过协调各同事角色实现协作行为
 * 3.Colleague 同事角色
 *     每一个同事角色都知道中介者角色，而且与其它的同事角色通信时候，一定通过中介者角色协作。
 *     同事类行为：
 *     （1）.同事类本身行为，比如改变对象本身的状态，处理自己的行为等，自有方法
 *     （2）.必须依赖中介者才能完成的行为 ，依赖方法
 * Created by hyz84 on 16/12/20.
 */
//抽象中介者
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
//具体中介者
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
//抽象同事类
public abstract class Colleague {
    protected Mediator mMediator;
    public Colleague(Mediator _mediator){
        this.mMediator=_mediator;
    }
}
//同事类
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
//同事类
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