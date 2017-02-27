package com.sh.lynn.hz.developbox.netframe.okhttp;

import android.text.TextUtils;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/1.
 */
public class NetSecrty {

    /**生成请求数据
     * */
    public static JSONObject endcodeRequest(JSONObject param){
        if(param == null){
            return null;
        }
        JSONObject json = new JSONObject();
        try {
//            String content =  Des3.encode(param.toString(), Config.CLIENT_KEY);
//            String sign = Md5.getMd5(Config.CLIENT_ID+content+ Md5.getMd5(Config.CLIENT_KEY));
//            json.putOpt("client_id", Config.CLIENT_ID);
//            json.putOpt("content",content);
//            json.putOpt("sign",sign);
            return json;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密响应数据
     * */
    public static NetResult decodeResponse(JSONObject response){
        NetResult result = new NetResult();
        result.setState(NetResult.CODE_ERROR);
        String return_code = response.optString("return_code");
        if (!TextUtils.isEmpty(return_code)){//
            result.setErr_msg(response.optString("return_msg"));//错误消息
        }else{//解析内部数据
            String secrytyConent = response.optString("content");
            try {
//                String content = Des3.decode(secrytyConent,Config.CLIENT_KEY);
//                if (Config.tag > 1) Log.d("decode content", content);
//                JSONObject param = new JSONObject(content);
//                //通讯数据
//                if (!"SUCCESS".equals(param.optString("return_code")) ){
//                    result.setErr_msg(param.optString("return_msg"));//错误消息
//                }else{
//                    //业务数据
//                    if ("SUCCESS".equals(param.optString("result_code"))){
//                        result.setState(NetResult.CODE_OK);
//                        result.setContent(param);
//                    }else{
//                        result.setErr_code(param.optString("err_code"));
//                        result.setErr_msg(param.optString("result_msg"));
//                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setErr_msg("解析错误:0");
            }
        }
        return result;
    }
}
