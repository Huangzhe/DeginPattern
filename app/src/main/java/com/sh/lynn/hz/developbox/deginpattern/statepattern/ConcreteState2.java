package com.sh.lynn.hz.developbox.deginpattern.statepattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * Created by apple on 17-2-1.
 */

public class ConcreteState2 extends State {
    @Override
    public void handle1() {
        //设置当前状态为state1
        super.context.setCurrentState(MyContext.STATE1);
        //过渡到state2，由Context实现
        super.context.handle1();
    }

    @Override
    public void handle2() {
        //本状态下处理的业务逻辑
        tempBuffer.append("ConcreteState2,dothing\n");
    }
}
