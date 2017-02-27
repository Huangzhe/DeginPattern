package com.sh.lynn.hz.developbox.deginpattern.bridgepattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * Created by apple on 17-2-1.
 */

public class RefinedAbstraction extends Abstraction {
    //复写构造函数
    public RefinedAbstraction(Implementor _imp) {
        super(_imp);
    }

    /**
     * 修正父类行为
     */
    @Override
    public void request() {
        //业务处理

        tempBuffer.append("RefinedAbstraction request\n");
        super.request();
        super.getImplementor().doAnything();
    }
}
