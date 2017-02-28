package com.sh.lynn.hz.developbox.netframe.okhttp;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/1.
 *
 *  当sate == code_error时，取err_code获取错误代码，错误消息为err_msg
 *  err_code 可为空,只有业务错误时，才会有数据
 *  业务参数在content中
 */
public class NetResult {
    public final static int CODE_OK = 0x000;
    public final static int CODE_ERROR = 0x001;

    private int state;//当前状态
    private String err_msg;//错误消息
    private String err_code;//错误代码
    private JSONObject content;//消息内容

    public NetResult(){
        state = CODE_ERROR;
        err_msg = "";
        err_code = "";
        content = new JSONObject();
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public JSONObject getContent() {
        return content;
    }
}
