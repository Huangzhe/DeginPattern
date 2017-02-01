/**
 * 享元模式 Flyweight Pattern 是池技术的重要实现方式
 *         使用共享对象可有效地支持大量的细粒度的对象。
 * 对象的内部状态和外部状态
 *       内部状态 intrinsic：内部状态是对象可共享出来的信息，
 *       存储在享元对象内部并且不随环境改变而改变。
 *       外部状态 extrinsic :外部状态是对象得以依赖的一个标记，是随环境改变而改变的
 * 角色：
 *      Flyweight:抽象享元角色 一个产品的抽象类，同时定义出对象的外部状态和内部状态的接口或实现。
 *      ConcreteFlyweight:具体享元角色
 *      unsharedConcreteFlyweight:不可共享的享元角色
 *      FlyweightFactory:享元工厂 构建一个池容器，同时提供从池中获取对象的方法
 *
 * 使用场景：系统中存在大量的相似对象；
 *          细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关
 *          需要缓冲池的场景
 * Created by apple on 17-2-1.
 */

public class FlyweightPattern {
}
/**
 * 抽象享元角色
 * Created by apple on 17-2-1.
 */

public abstract class Flyweight {
    //内部状态
    private String intrinsic;
    //外部状态
    protected  final String extrinsic;
    //要求享元角色必须接受外部状态
    public Flyweight(String _extrinsic){
        extrinsic=_extrinsic;
    }
    //定义业务操作
    public abstract void operate();

    public String getIntrinsic() {
        return intrinsic;
    }

    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}
public class ConcreteFlyweight1 extends Flyweight {
    //接受外部状态
    public ConcreteFlyweight1(String _extrinsic){
        super(_extrinsic);
    }

    @Override
    public void operate() {
        //业务逻辑
    }
}
public class ConcreteFlyweight2 extends Flyweight {
    //接受外部状态
    public ConcreteFlyweight2(String _extrinsic){
        super(_extrinsic);
    }

    @Override
    public void operate() {
        //业务逻辑
    }
}
public class FlyweightFactory {
    //定义一个池容器
    private static HashMap<String,Flyweight> pool = new HashMap<>();
    //享元工厂
    public static Flyweight getFlyweight(String extrinsic){
        //需要返回的工厂
        Flyweight flyweight=null;
        if(pool.containsKey(extrinsic)){
            flyweight =pool.get(extrinsic);
        }else{
            flyweight = new ConcreteFlyweight1(extrinsic);
            pool.put(extrinsic,flyweight);

        }
        return flyweight;
    }
}