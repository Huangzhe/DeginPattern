package com.sh.lynn.hz.developbox.deginpattern.flyweightpattern;

import java.util.HashMap;

/**
 * 享元工厂
 * Created by apple on 17-2-1.
 */

public class FlyweightFactory {
    //定义一个池容器
    private static HashMap<String,Flyweight> pool = new HashMap<>();
    //享元工厂
    public static Flyweight getFlyweight(String extrinsic){
        //需要返回的工厂
        Flyweight flyweight=null;
        if(pool.containsKey(extrinsic)){
            flyweight =pool.get(extrinsic);
        }else{
            flyweight = new ConcreteFlyweight1(extrinsic);
            pool.put(extrinsic,flyweight);

        }
        return flyweight;
    }
}
