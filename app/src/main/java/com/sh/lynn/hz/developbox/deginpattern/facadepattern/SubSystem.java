package com.sh.lynn.hz.developbox.deginpattern.facadepattern;

import static com.sh.lynn.hz.developbox.utils.Utils.tempBuffer;

/**
 * 子系统
 * Created by hyz84 on 17/1/11.
 */

public class SubSystem {
    public static class ClassA{
        public void doSomething(){
            tempBuffer.append("classA A A 。。。");
        }
    }
    public static class ClassB{
        public void doSomething(){
            tempBuffer.append("classB B B 。。。");
        }
    }
    public static class ClassC{
        public void doSomething(){
            tempBuffer.append("classC C C 。。。");
        }
    }
}
