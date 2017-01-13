package com.sh.lynn.hz.developbox.deginpattern.strategypattern;

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
