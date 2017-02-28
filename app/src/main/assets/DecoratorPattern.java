/**
 * 定义：动态的给一个对象添加一些额外的职责。就增加功能来说，装饰模式相比生成子类更为灵活。
 * 四个角色：
 * Component 抽象构件 是一个接口或者抽象类，就是定义我们最核心的对象，也就是最原始的对象。
 * ConcreteComponent 具体构件 最核心，最原始，最基本的接口或抽象类的实现，你要装饰的就是它。
 * Decorator 装饰角色 一个抽象类或者抽象方法，它的属性里必然有一个private 变量指向Component抽象构件。
 * ConcreteDecoratorA 具体装饰角色 把最核心，最原始的，最基本的东西装饰成其他东西。
 * 优点：
 * 装饰类和被修饰类可以独立发展，而不会相互耦合。
 * 装饰模式是继承关系的一个替代方案。
 * 装饰模式可以动态的扩展一个实现类的功能。
 * 缺点：
 * 多层的装饰是比较复杂的。
 * 使用场景：
 * 需要扩展一个类的功能，或给一个类增加附加功能。
 * 需要动态地给一个对象增加功能，这些功能可以动态的撤销。
 * 需要为一批的兄弟类进行改装或加装功能。
 *  Created by hyz84 on 17/1/5.
 */

public class DecoratorPatteern {
    public String getResult(){
        Component component = new ConcreteComponent();
        component = new ConcreteDecorator1(component);
        component = new ConcreteDecorator2(component);
        return  component.operate();
    }
}

/**
 * 抽象构件
 * Created by hyz84 on 17/1/5.
 */

public abstract class Component {
    //抽象的方法
    public abstract String operate();
}
/**
 *具体构件 最核心，最原始，最基本的接口或抽象类的实现，你要装饰的就是它
 * Created by hyz84 on 17/1/5.
 */
public class ConcreteComponent extends Component {
    @Override
    public String operate() {
        return "ConcreteComponent do Something!";
    }
}

/**装饰角色
 * Created by hyz84 on 17/1/5.
 */

public abstract class Decorator extends Component {
    private Component mComponent=null;
    public Decorator(Component _component){
        mComponent=_component;
    }
    @Override
    public String operate() {
        return mComponent.operate();
    }
}


/**
 * 具体装饰角色
 * Created by hyz84 on 17/1/5.
 */

public class ConcreteDecorator1 extends Decorator {
    //定义被修饰者
    public ConcreteDecorator1(Component _component) {
        super(_component);
    }
    //定义自己的方法
    private String method1(){
        return "methdo1 修饰";
    }
    public String operate(){
        return   method1()+super.operate();

    }
}

public class ConcreteDecorator2 extends Decorator {

    //定义被修饰者
    public ConcreteDecorator2(Component _component) {
        super(_component);
    }
    //定义自己的方法
    private String method1(){
        return "methdo2 修饰";
    }
    public String operate(){
        return   method1()+super.operate();

    }
}