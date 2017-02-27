package com.sh.lynn.hz.developbox.deginpattern.statepattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * Created by apple on 17-2-1.
 */

public class ConcreteState1 extends State {
    @Override
    public void handle1() {
        //本状态下必须处理的逻辑

        tempBuffer.append("ConcreteState1,dothing\n");
    }

    @Override
    public void handle2() {
        //设置当前状态为state2
        super.context.setCurrentState(MyContext.STATE2);
        //过渡到state2，由Context实现
        super.context.handle2();
    }
}
