/**
 * 桥梁模式 也叫桥接模式 将抽象和实现解耦，使得两者可以独立的变化
 * 角色：
 *      Abstraction:抽象化角色，定义出该角色的行为，同时保存一个对实现化角色的应用，一般抽象类。
 *      Implementor:实现化角色 接口或者抽象类，定义角色必须的行为和属性
 *      RefinedAbstraction:修正抽象化角色 它引用实现化角色对抽象化角色进行修正。
 *      ConcreteImplementor:具体实现化角色
 * Created by apple on 17-2-1.
 */

public class BridgePattern {

    public void excuExample(){
        Implementor implementor = new ConcreteImplementor1();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.request();
    }
}
/**
 * 实现化角色
 * Created by apple on 17-2-1.
 */

public interface Implementor {
    //基本方法
    public void doSomething();
    public void doAnything();
}
/**
 * 抽象化角色
 * Created by apple on 17-2-1.
 */

public abstract class Abstraction {
    //定义对实现化角色的引用
    private Implementor implementor;
    public Abstraction(Implementor _imp){
        implementor=_imp;
    }
    //自身行为和属性
    public void request(){
        this.implementor.doSomething();
    }

    /**
     * 获取实现化角色
     * @return
     */
    public Implementor getImplementor() {
        return implementor;
    }
}
/**
 * Created by apple on 17-2-1.
 */

public class ConcreteImplementor1 implements Implementor {
    @Override
    public void doSomething() {
        tempBuffer.append("ConcreteImplementor1 doSomething\n");
    }

    @Override
    public void doAnything() {
        tempBuffer.append("ConcreteImplementor1 doAnything\n");
    }
}
public class ConcreteImplementor2 implements Implementor {
    @Override
    public void doSomething() {
        tempBuffer.append("ConcreteImplementor2 doSomething\n");
    }

    @Override
    public void doAnything() {
        tempBuffer.append("ConcreteImplementor2 doAnything\n");
    }
}
public class RefinedAbstraction extends Abstraction {
    //复写构造函数
    public RefinedAbstraction(Implementor _imp) {
        super(_imp);
    }

    /**
     * 修正父类行为
     */
    @Override
    public void request() {
        //业务处理

        tempBuffer.append("RefinedAbstraction request\n");
        super.request();
        super.getImplementor().doAnything();
    }
}