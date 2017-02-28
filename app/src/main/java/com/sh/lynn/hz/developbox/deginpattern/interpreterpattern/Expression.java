package com.sh.lynn.hz.developbox.deginpattern.interpreterpattern;

/**
 * 抽象表达式
 * Created by apple on 17-2-1.
 */

public abstract class Expression {
    //每个表达式必须有一个解析任务
    public abstract Object interpreter(MyContext context);
}
