package com.sh.lynn.hz.developbox.deginpattern.visitorpattern;

import java.util.Random;

/**
 * 结构对象
 * Created by apple on 17-1-31.
 */

public class ObjectStructure {
    /**
     * 对象生成器
     * @return
     */
    public static Element createElement(){
        Random rand = new Random();
        if(rand.nextInt(100)>50){
            return new CocreteElement1();
        }else{
            return new CocreteElement2();
        }
    }
}
