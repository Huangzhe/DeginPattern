/**
 * 定义：将对象组合成树形结构以表示"部分—整体"的层次结构使得用户对单个对象和组合对象的使用具有一致性。
 * 角色：
 * Component 抽象构件角色 定义参加组合对象的共有方法和属性，可以定义一些默认行为或属性；
 * Leaf 叶子构件 叶子对象，其下再也没有其他的分支
 * Composite 树枝构件 树枝对象，它的作用是组合树枝节点和叶子节点形成一个树状结构
 * 优点：高层模块调用简单，节点自由增加
 * 缺点：直接定义了树叶和树枝的实现类，与依赖倒置原则冲突
 * 使用场景：
 * 维护和展示部分-整体关系的场景，如树形菜单，文件和文件夹管理
 * 从一个整体中能够独立出部分模块或功能的场景
 * Created by hyz84 on 17/1/10.
 */

public class CompositePattern {
    StringBuffer mBuffer = new StringBuffer();
    public String showExample() {

        Composite root = new Composite();
        mBuffer.append(root.doSomething() + "\n");
        Composite branch = new Composite();
        Leaf leaf = new Leaf();
        root.add(branch);
        branch.add(leaf);
        display(root);
        return mBuffer.toString();
    }
    private void display(Composite _root){
        for (Component component:_root.getChildren()){
            if(component instanceof  Leaf){
                mBuffer.append( component.doSomething()+"\n");
            }else{
                display((Composite)component);
            }
        }
    }
}

public abstract class Component {
    //个体和整体都具有的共享
    public String doSomething(){
        //业务逻辑
        return "Component";
    }
}

/**
 * 树枝构件
 * Created by hyz84 on 17/1/10.
 */
public class Composite extends Component {
    private ArrayList<Component> mComponentArrayList = new ArrayList<>();

    /**
     * 增加一个叶子构件或者树枝构件
     *  @param _component
     */
    public void add(Component _component) {
        mComponentArrayList.add(_component);
    }

    public void remove(Component _component){
        mComponentArrayList.remove(_component);
    }

    public ArrayList<Component> getChildren(){
        return mComponentArrayList;
    }
}

/**树叶构件
 * Created by hyz84 on 17/1/10.
 */
public class Leaf extends Component {
    /**
     * 可以复写父类方法
     * @return
     */
    public  String doSomething(){
        return "leaf ";
    }
}