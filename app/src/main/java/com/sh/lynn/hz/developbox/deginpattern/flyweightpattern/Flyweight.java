package com.sh.lynn.hz.developbox.deginpattern.flyweightpattern;

/**
 * 抽象享元角色
 * Created by apple on 17-2-1.
 */

public abstract class Flyweight {
    //内部状态
    private String intrinsic;
    //外部状态
    protected  final String extrinsic;
    //要求享元角色必须接受外部状态
    public Flyweight(String _extrinsic){
        extrinsic=_extrinsic;
    }
    //定义业务操作
    public abstract void operate();

    public String getIntrinsic() {
        return intrinsic;
    }

    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}
