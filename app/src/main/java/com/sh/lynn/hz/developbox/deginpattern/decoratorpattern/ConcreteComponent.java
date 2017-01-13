package com.sh.lynn.hz.developbox.deginpattern.decoratorpattern;

/**
 * Created by hyz84 on 17/1/5.
 */

public class ConcreteComponent extends Component {
    @Override
    public String operate() {
        return "ConcreteComponent do Something!";
    }
}
