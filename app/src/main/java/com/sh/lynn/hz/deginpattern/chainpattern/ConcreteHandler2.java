package com.sh.lynn.hz.deginpattern.chainpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public class ConcreteHandler2 extends Handler {
    @Override
    protected Level getHandlerLevel() {
        return Level.MID;
    }

    @Override
    protected Response echo(Request request) {
        Response response = new Response();
        response.setResult(request.getMsg());
        return response;
    }
}
