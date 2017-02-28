package com.sh.lynn.hz.developbox.deginpattern.flyweightpattern;

/**
 * Created by apple on 17-2-1.
 */

public class ConcreteFlyweight1 extends Flyweight {
    //接受外部状态
   public ConcreteFlyweight1(String _extrinsic){
       super(_extrinsic);
   }

    @Override
    public void operate() {
        //业务逻辑
    }
}
