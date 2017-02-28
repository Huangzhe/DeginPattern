package com.sh.lynn.hz.developbox.event;

/**
 * Created by Administrator on 2016/4/28.
 */
public class BaseEvent {
    public enum EVENT_STATUS {
        EVENT_OK,
        EVENT_ERROR
    }

    EVENT_STATUS status;
    Object msg;

    String tag = "";

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public void setStatus(EVENT_STATUS status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public EVENT_STATUS getStatus() {
        return status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String mtag) {
        this.tag = mtag;
    }

}
