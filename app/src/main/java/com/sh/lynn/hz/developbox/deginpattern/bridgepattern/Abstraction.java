package com.sh.lynn.hz.developbox.deginpattern.bridgepattern;

/**
 * 抽象化角色
 * Created by apple on 17-2-1.
 */

public abstract class Abstraction {
    //定义对实现化角色的引用
    private Implementor implementor;
   public Abstraction(Implementor _imp){
       implementor=_imp;
   }
    //自身行为和属性
    public void request(){
        this.implementor.doSomething();
    }

    /**
     * 获取实现化角色
     * @return
     */
    public Implementor getImplementor() {
        return implementor;
    }
}
