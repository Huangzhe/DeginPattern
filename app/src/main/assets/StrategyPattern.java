/**定义：定义一组算法，将每个算法都封装起来，并且使它们之间可以互换
 * 策略模式三个角色：
 * Context封装角色：
 * 它也叫上下文角色，起承上启下封装作用，屏蔽高层模块对策略，算法的直接访问，封装可能存在变化。
 * Strategy 抽象策略角色 ：
 * 策略 算法家族的抽象，通常为接口，定义每个策略或算法必须具有的方法和属性。
 * ConcreteStrategy1 具体策略角色
 *优点：
 * 1.算法可以自由切换
 * 2.避免多重条件判断
 * 3.扩展性良好
 * 缺点：
 * 1.策略类数量增多
 * 2.所有策略都需要对外暴露
 * 使用场景：
 * 1.多个类只有在算法或行为上稍有不同的场景
 * 2.算法需要自由切换的场景
 * 3.需要屏蔽算法规则的场景
 * Created by hyz84 on 17/1/8.
 */

public class StrategyPattern {
    Strategy mStrategy=null;
    StrategyContext mStrategyContext;
    public String  excuStrategy1(){
        mStrategy = new ConcreteStrategy1();
        mStrategyContext = new StrategyContext(mStrategy);
        return mStrategyContext.doAnything();
    }

    public String  excuStrategy2() {
        mStrategy = new ConcreteStrategy2();
        mStrategyContext = new StrategyContext(mStrategy);
        return mStrategyContext.doAnything();
    }

    public String  excuStrategy3(String symbol,int a,int b){
        switch (symbol){
            case "+":
                return "计算结果："+   Calculator.ADD.exec(a,b);

            case "-":
                return "计算结果："+  Calculator.SUB.exec(a,b);
            default:
                return "";
        }

    }
}
/**
 * 抽象策略
 * Created by hyz84 on 17/1/8.
 */

public interface Strategy {
    //策略模式的运算法则
    public String doSomething();
 }

public class ConcreteStrategy1 implements Strategy {
    @Override
    public String doSomething() {
        return "具体策略1的运算法则";
    }
}

public class ConcreteStrategy2 implements Strategy {
    @Override
    public String doSomething() {
        return "具体策略2的运算法则";
    }
}

/**策略上下文
 * Created by hyz84 on 17/1/8.
 */

public class StrategyContext {
    private Strategy mStrategy=null;
    public StrategyContext(Strategy _strategy){
        mStrategy = _strategy;
    }
    //封装后的策略方法
    public String doAnything(){
        return   mStrategy.doSomething();
    }
}
/**************************************************************/
/**
 * 策略枚举
 * Created by hyz84 on 17/1/8.
 */

public enum Calculator {
    //加法
    ADD ("+"){
        public int exec(int a,int b){
            return a+b;   }
    },
    //减法
    SUB("-"){
        public int exec(int a,int b){
            return a-b;
        }
    };
    String  mValue="";
    //定义成员值类型
    private Calculator(String _value){
        mValue=_value;
    }
    //获得枚举成员的值
    public String getValue(){
        return mValue;
    }

    public abstract int exec(int a,int b);
}
