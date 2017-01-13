package com.sh.lynn.hz.developbox.deginpattern.mediator;

/**
 * Created by hyz84 on 16/12/20.
 */

public abstract class Colleague {
    protected Mediator mMediator;
    public Colleague(Mediator _mediator){
        this.mMediator=_mediator;
    }
}
