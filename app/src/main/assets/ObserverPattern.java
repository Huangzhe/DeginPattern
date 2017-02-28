/**
 * 也叫订阅模式(publish/subscribe)
 * 定义：定义对象间的一种一对多的依赖关系，使得每当一个对象改变状态，
 *      则所有依赖于它的对象都会得到通知并被自动更新。
 * 角色：
 * Subject 被观察者 定义被观察者必须实现的职责，他必须能动态的增加，取消观察者。
 * Oberver 观察者 观察者接收到消息后，即进行update操作，对接收到信息进行处理。
 * ConcreteSubject 具体的被观察者
 * ConcreteObserver 具体的观察者
 * Created by hyz84 on 17/1/11.
 */

public class ObserverPattern {

    public  void excuExample(){
        tempBuffer.delete(0,tempBuffer.length());
        ConcreteSubject subject = new ConcreteSubject();
        Observer mOberver = new ConcreteObserver();
        subject.addObserver(mOberver);
        subject.doSomething();
    }
}
public abstract class Subject {
    //定义一个观察者集合
    private Vector<Observer> mObservers = new Vector<>();
    //添加一个观察者
    public void addObserver(Observer _observer){
        mObservers.add(_observer);
    }
    //删除一个观察者
    public void delObserver(Observer _observer){
        mObservers.remove(_observer);
    }
    //通知所有观察者
    public void notifyObservers(){
        for(Observer o:mObservers){
            o.update();
        }
    }
}
public class ConcreteSubject extends Subject {
    public void doSomething(){
        super.notifyObservers();
        tempBuffer.append("ConcreteSubject doSomething！ \n");
    }
}
public interface Observer {
    //更新方法
    public void update();
}

public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        tempBuffer.append("ConcreteObserver,收到信息，并进行处理\n");
    }
}