package com.sh.lynn.hz.developbox.deginpattern.visitorpattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * Created by apple on 17-1-31.
 */

public class CocreteElement2 extends Element {
    @Override
    public void doSomething() {
        //业务逻辑
        tempBuffer.append("CocreteElement2 dothing.....\n");
    }

    /**
     * 允许那个访问者访问
     * @param _visitor
     */
    @Override
    public void accept(IVisitor _visitor) {
        _visitor.visit(this);
    }
}
