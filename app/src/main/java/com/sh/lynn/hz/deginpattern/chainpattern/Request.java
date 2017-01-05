package com.sh.lynn.hz.deginpattern.chainpattern;

/**
 * Created by hyz84 on 16/12/28.
 */

public class Request {
    private Level mLevel;
    private String msg ;
    public Request(Level _level,String _msg){
        mLevel=_level;
        msg=_msg;
    }
    public Level getRequestLevel(){
        return mLevel;
    }

    public String getMsg(){
        return  msg;
    }
}
