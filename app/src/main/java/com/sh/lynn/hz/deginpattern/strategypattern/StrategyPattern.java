package com.sh.lynn.hz.deginpattern.strategypattern;

import static com.sh.lynn.hz.deginpattern.Utils.tempBuffer;

/**
 * 定义：定义一组算法，将每个算法都封装起来，并且使它们之间可以互换
 * 策略模式三个角色：
 * Context封装角色：
 * 它也叫上下文角色，起承上启下封装作用，屏蔽高层模块对策略，算法的直接访问，封装可能存在变化。
 * Strategy 抽象策略角色 ：
 * 策略 算法家族的抽象，通常为接口，定义每个策略或算法必须具有的方法和属性。
 * ConcreteStrategy1 具体策略角色
 * 优点：
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
    Strategy mStrategy = null;
    StrategyContext mStrategyContext;

    public void excuStrategy1() {
        mStrategy = new ConcreteStrategy1();
        mStrategyContext = new StrategyContext(mStrategy);
        tempBuffer.append(mStrategyContext.doAnything() + "\n");
    }

    public void excuStrategy2() {
        mStrategy = new ConcreteStrategy2();
        mStrategyContext = new StrategyContext(mStrategy);
        tempBuffer.append(mStrategyContext.doAnything() + "\n");
    }

    public void excuStrategy3() {
        String symbol = "+";
        int a = 3;
        int b = 1;
        switch (symbol) {
            case "+":
                tempBuffer.append("计算结果：" + Calculator.ADD.exec(a, b));
                break;
            case "-":
                tempBuffer.append("计算结果：" + Calculator.SUB.exec(a, b));
                break;
            default:
                break;
        }

    }
}
