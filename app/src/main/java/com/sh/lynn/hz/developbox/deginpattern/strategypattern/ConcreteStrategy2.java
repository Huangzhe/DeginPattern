package com.sh.lynn.hz.developbox.deginpattern.strategypattern;

/**
 * Created by hyz84 on 17/1/8.
 */

public class ConcreteStrategy2 implements Strategy {
    @Override
    public String doSomething() {
        return "具体策略2的运算法则";
    }
}
