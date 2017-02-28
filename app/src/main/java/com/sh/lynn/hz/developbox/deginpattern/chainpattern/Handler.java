package com.sh.lynn.hz.developbox.deginpattern.chainpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public abstract class Handler {
    private Handler nextHandler;
    //每个处理者都必须对请求做出处理
    public final Response handleMessage(Request request) {
        Response response = null;
        //判断是否是自己的处理级别
        if (getHandlerLevel().equals(request.getRequestLevel())) {
            response = echo(request);
        } else {
            //下一个处理者
            if (this.nextHandler != null) {
                response = this.nextHandler.handleMessage(request);
            } else {

            }
        }
        return response;
    }
//设置下一个处理者是谁
    public void setNextHandler(Handler _handler) {
        this.nextHandler = _handler;
    }
//每个处理者都有一个处理级别
    protected abstract Level getHandlerLevel();
//每个处理者都必须实现处理任务
    protected abstract Response echo(Request request);
}
