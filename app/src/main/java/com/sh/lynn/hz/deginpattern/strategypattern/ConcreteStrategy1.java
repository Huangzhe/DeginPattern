package com.sh.lynn.hz.deginpattern.strategypattern;

/**
 * Created by hyz84 on 17/1/8.
 */

public class ConcreteStrategy1 implements Strategy {
    @Override
    public String doSomething() {
        return "具体策略1的运算法则";
    }
}
