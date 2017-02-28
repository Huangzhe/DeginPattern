package com.sh.lynn.hz.developbox.deginpattern.observerpattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * Created by hyz84 on 17/1/11.
 */

public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        tempBuffer.append("ConcreteObserver,收到信息，并进行处理\n");
    }
}
