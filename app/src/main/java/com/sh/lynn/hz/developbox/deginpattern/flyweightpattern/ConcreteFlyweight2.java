package com.sh.lynn.hz.developbox.deginpattern.flyweightpattern;

/**
 * Created by apple on 17-2-1.
 */

public class ConcreteFlyweight2 extends Flyweight {
    //接受外部状态
   public ConcreteFlyweight2(String _extrinsic){
       super(_extrinsic);
   }

    @Override
    public void operate() {
        //业务逻辑
    }
}
