package com.sh.lynn.hz.developbox.deginpattern.bridgepattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * Created by apple on 17-2-1.
 */

public class ConcreteImplementor1 implements Implementor {
    @Override
    public void doSomething() {
        tempBuffer.append("ConcreteImplementor1 doSomething\n");
    }

    @Override
    public void doAnything() {
        tempBuffer.append("ConcreteImplementor1 doAnything\n");
    }
}
