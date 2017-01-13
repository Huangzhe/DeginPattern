package com.sh.lynn.hz.developbox.deginpattern.strategypattern;

/**
 * 策略枚举
 * Created by hyz84 on 17/1/8.
 */

public enum Calculator {
   //加法
    ADD ("+"){
    public int exec(int a,int b){
        return a+b;   }
    },
    //减法
    SUB("-"){
        public int exec(int a,int b){
            return a-b;
        }
    };
    String  mValue="";
    //定义成员值类型
    private Calculator(String _value){
        mValue=_value;
    }
    //获得枚举成员的值
    public String getValue(){
       return mValue;
    }

    public abstract int exec(int a,int b);
}
