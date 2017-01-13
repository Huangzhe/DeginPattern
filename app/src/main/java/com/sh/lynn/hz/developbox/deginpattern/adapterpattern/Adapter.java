package com.sh.lynn.hz.developbox.deginpattern.adapterpattern;

import com.sh.lynn.hz.developbox.deginpattern.adapterpattern.Adaptee;

/**
 * Created by hyz84 on 17/1/8.
 */

public class Adapter extends Adaptee implements Target {
    @Override
    public String request() {
        return super.doSomethig();
    }
}
