

/**
 * 访问者模式：封装一些作用于某种数据结构中的各种元素操作，他可以在不改变数据结构的前提下作用于这些元素的新的操作。
 * 角色:
 *      Visitor 抽象访问者:抽象类或者接口，声明访问者可以访问哪些元素；
 *      ConcreteVisitor 具体访问者
 *      Element 抽象元素 接口或者抽象类，声明接受哪一类访问者访问，程序上通过accept方法中的参数来定义的。
 *      ConcreteElement 具体元素 实现accept方法，通常是visitor.visit(this)
 *      ObjectStruture 结构对象 元素产生者，一般容纳在多个不同类，不同接口的容器
 *
 * 优点：符合单一职责原则
 *      优秀的拓展性
 *      灵活性非常高
 * 缺点：具体元素变更比较困难
 *      具体元素对 访问者公布细节
 *      违背了依赖倒置原则
 * 使用场景：一个对象结构包含很多类对象，它们有不同的接口，而你想对这些对象实施一些依赖于其具体类的操作。
 *          需要对一个对象结构中的对象进行很多不同并且不相关的操作，而你想避免这些操作"污染"这些对象的类。
 *          总而言之，在这种情况下一定要考虑使用访问者模式：业务规则要求遍历多个不同的对象。
 * 最佳实践：访问者模式是一种集中规整模式，特别适用于大规模重构项目
 * Created by apple on 17-1-31.
 */

public class VisitorPattern {
    public  void excuExample(){
        for(int i=0;i<10;i++){
            Element element = ObjectStructure.createElement();
            element.accept(new Visitor());
        }
    }
}
/**
 * 抽象元素
 * Created by apple on 17-1-31.
 */

public abstract class Element {
    public abstract void doSomething();
    public abstract void accept(IVisitor _visitor);
}
/**
 * 具体元素
 * Created by apple on 17-1-31.
 */

public class CocreteElement1 extends Element {
    @Override
    public void doSomething() {
        //业务逻辑
        tempBuffer.append("CocreteElement1 dothing");
    }

    /**
     * 允许那个访问者访问
     * @param _visitor
     */
    @Override
    public void accept(IVisitor _visitor) {
        _visitor.visit(this);
    }
}
/**
 * 具体元素
 * Created by apple on 17-1-31.
 */

public class CocreteElement2 extends Element {
    @Override
    public void doSomething() {
        //业务逻辑
        tempBuffer.append("CocreteElement2 dothing");
    }

    /**
     * 允许那个访问者访问
     * @param _visitor
     */
    @Override
    public void accept(IVisitor _visitor) {
        _visitor.visit(this);
    }
}

/**
 * 抽象访问者
 * Created by apple on 17-1-31.
 */

public interface IVisitor {
    public void visit(CocreteElement1 element);
    public void visit(CocreteElement2 element);
}
/**
 * Created by apple on 17-1-31.
 */

public class Visitor implements IVisitor {
    /**
     * 访问element1
     * @param element
     */
    @Override
    public void visit(CocreteElement1 element) {
        element.doSomething();
    }

    /**
     * 访问Element2
     * @param element
     */
    @Override
    public void visit(CocreteElement2 element) {
        element.doSomething();
    }
}

/**
 * 结构对象
 * Created by apple on 17-1-31.
 */

public class ObjectStructure {
    /**
     * 对象生成器
     * @return
     */
    public static Element createElement(){
        Random rand = new Random();
        if(rand.nextInt(100)>50){
            return new CocreteElement1();
        }else{
            return new CocreteElement2();
        }
    }
}