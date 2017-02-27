package com.sh.lynn.hz.developbox.deginpattern.interpreterpattern;

/**
 * 非终结符表达式
 * Created by apple on 17-2-1.
 */

public class NonTerminalExpression extends Expression {
    //每个非终结符表达式都会对其他表达式产生依赖
    public NonTerminalExpression(Expression... expressions){

    }

    @Override
    public Object interpreter(MyContext context) {
        //进行文法处理
        return null;
    }
}
