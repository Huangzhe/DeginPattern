package com.sh.lynn.hz.developbox.deginpattern.facadepattern;

/**
 * 门面
 * Created by hyz84 on 17/1/11.
 */

public class Facade {
    //被委托对象
    private SubSystem.ClassA mA = new SubSystem.ClassA();
    private SubSystem.ClassB mB = new SubSystem.ClassB();
    private SubSystem.ClassC mC = new SubSystem.ClassC();

    //提供给外部访问的方法
    public void methodA() {
        mA.doSomething();
    }

    public void methodB() {
        mB.doSomething();
    }

    public void methodC() {
        mC.doSomething();
    }
}
