package com.sh.lynn.hz.deginpattern.decoratorpattern;

/**
 * Created by hyz84 on 17/1/5.
 */

public abstract class Decorator extends Component {
    private Component mComponent=null;
    public Decorator(Component _component){
        mComponent=_component;
    }
    @Override
    public String operate() {
        return mComponent.operate();
    }
}
