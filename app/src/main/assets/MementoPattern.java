
/**
 * 备忘录模式：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 *            这样以后就可以将该对象恢复到原先保存状态。通俗的说就是一个对象的备份模式，
 *            提供一种程序数据的备份方法。
 * 备忘录模式的角色：
 *          Originator:发起人角色 记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。
 *          Memento：备忘录角色 负责存储Originator发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。
 *          Caretaker:备忘录管理员角色  对备忘录进行管理、保存和提供备忘录
 *使用场景：
 *      1.需要保存和恢复数据的相关场景
 *      2.提供一个可回滚的操作
 *      3.需要监控的副本场景中
 *      4.数据库连接的事物
 * 注意事项：
 *     备忘录的生命周期，备忘录的性能
 *
 * Created by apple on 17-1-31.
 */

public class MementoPattern {
    public void execuExample(){
        tempBuffer.delete(0,tempBuffer.length());
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("First State!");
        caretaker.setMemento(originator.createMemento());
        originator.setState("Second State!");
        originator.restoreMemento(caretaker.getMemento());
        String state = originator.getState();
        tempBuffer.append("恢复：current state:"+state);
    }
}

/**
 * 发起人角色
 * Created by apple on 17-1-31.
 */

public class Originator {
    //内部状态
    private String state="1";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        tempBuffer.append("current state:"+state);
    }

    /**
     * 创建一个备忘录
     * @return
     */
    public Memento createMemento(){
        return new Memento(state);
    }

    /**
     * 恢复备忘录
     * @param _memento
     */
    public void restoreMemento(Memento _memento){
        this.setState(_memento.getState());
    }
}

/**
 * 备忘录角色
 * Created by apple on 17-1-31.
 */

public class Memento {
    private String state="";
    public Memento(String _state){
        this.state=_state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 备忘录管理员角色
 * Created by apple on 17-1-31.
 */

public class Caretaker {
    private Memento memento;
    public Memento getMemento(){
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}