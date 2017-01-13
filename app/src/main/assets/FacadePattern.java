/**门面模式 Facade Pattern ,也叫外观模式。
 * 定义：要求一个子系统的外部与其内部通信必须通过一个统一的对象进行。
 *        简单的说，门面对象是外部访问子系统内部唯一的通道。
 * 角色：
 * Facade门面角色
 * subsystem 子系统角色
 * 优点：
 * 减少系统的相互依赖
 * 提高了灵活性
 * 提高安全性
 * 缺点： 不符合开闭原则
 * 使用场景：为一个复杂的模块或子系统提供一个供外界访问的接口
 * 子系统相对独立
 * 预防低水平人员带来的风险扩散
 * Created by hyz84 on 17/1/11.
 */

public class FacadePattern {
    public void excuSample(){
        Facade mFacade = new Facade();
        mFacade.methodA();
        mFacade.methodB();
        mFacade.methodC();
    }
}

/**
 * 门面
 * Created by hyz84 on 17/1/11.
 */

public class Facade {
    //被委托对象
    private SubSystem.ClassA mA = new SubSystem.ClassA();
    private SubSystem.ClassB mB = new SubSystem.ClassB();
    private SubSystem.ClassC mC = new SubSystem.ClassC();

    //提供给外部访问的方法
    public void methodA() {
        mA.doSomething();
    }

    public void methodB() {
        mB.doSomething();
    }

    public void methodC() {
        mC.doSomething();
    }
}

/**
 * 子系统
 * Created by hyz84 on 17/1/11.
 */

public class SubSystem {
    public static class ClassA{
        public void doSomething(){
            tempBuffer.append("classA A A 。。。\n");
        }
    }
    public static class ClassB{
        public void doSomething(){
            tempBuffer.append("classB B B 。。。\n");
        }
    }
    public static class ClassC{
        public void doSomething(){
            tempBuffer.append("classC C C 。。。");
        }
    }
}