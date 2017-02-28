package com.sh.lynn.hz.developbox.deginpattern.statepattern;

/**
 * 状态模式：当一个对象内部状态改变时，允许其改变行为，
 *          这个对象看起来像改变了其类。
 * 角色：
 *     State ：抽象状态角色 接口或抽象类，负责对象状态定义，并且
 *              封装环境角色以实现状态切换。
 *     ConcreteState: 具体状态角色 每一个具体状态必须完成两个职责
 *         本状态行为管理以及趋向状态处理，通俗说就是：本状态下要做的事情
 *         和如何过度到下一个状态。
 *     Context： 环境角色 定义客户端需要的接口，并且负责具体状态的切换
 *          把状态对象声明为静态常量，有几个状态对象就声明几个静态常量。
 *          环境角色具有状态抽象角色定义的所有行为，具体执行使用委托方式
 * 优点:结构清晰
 *      遵循设计原则
 *      封装性非常好
 * 缺点：子类太多，类膨胀
 * 使用场景：行为随状态改变而改变的场景
 *          条件、分支判断语句的替代者
 *
 *
 * Created by apple on 17-2-1.
 */

public class StatePattern {

    public void excuExample(){
        //定义环境变量
        MyContext context = new MyContext();
        //初始化状态
        context.setCurrentState(new ConcreteState1());
        //行为执行
        context.handle1();
        context.handle2();
    }
}
