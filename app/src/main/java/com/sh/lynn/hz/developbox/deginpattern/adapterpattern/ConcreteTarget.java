package com.sh.lynn.hz.developbox.deginpattern.adapterpattern;

/**
 * Created by hyz84 on 17/1/8.
 */

public class ConcreteTarget implements Target {
    @Override
    public String request() {
        return "This is ConcreteTarget! ";
    }
}
