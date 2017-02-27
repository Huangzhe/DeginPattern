package com.sh.lynn.hz.developbox.netframe.okhttp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/9.
 *
 * 网络请求参数工具类
 *
 */

public class RequestParam {
    public static RequestParam build(){
        return new RequestParam();
    }

    JSONObject base;
    JSONObject param;

    public RequestParam(){
        base = new JSONObject();
        param = new JSONObject();
    }

    /**设置业务层信息
     * */
    public void put(String key, Object value){
        try {
            param.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**设置基础层信息
     * */
    public void putBase(String key, Object value){
        try {
            base.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initCommon(){
        //set base json infomation
//        nonce_str	随机字符串	String(32)	随机字符串，不长于32位。推荐随机数生成算法	不可空	5K8264ILTKCH16CQ2502S
//                I8ZNMTM67VS
//        pos_type	手机类型	String(2)		01=安卓手机
//        sessionID	会话ID	String	发送短信验证码成功返回的sessionID信息	可空
//        custId	用户号	String		可空
//        userMP	用户手机号码	String		不可空
//        mpIemi	用户手机IMEI	String		不可空
//        app_version	App版本号	String		不可空

//            base.put("app_version", UserHelper.getAppVersion());//   App版本号	String		不可空
//            base.put("custId", UserHelper.getCustId());//id
//            base.put("userMP", UserHelper.getCustPhone());//手机号
//            base.put("mpIemi", UserHelper.getUniquePsuedoID());//设备唯一号
//            base.put("sessionID", UserHelper.getSessionID());
//            base.put("clientGps", Config.gps);//gps
//            base.put("nonce_str", StringUtil.nonce_str());
//            base.put("pos_type", "01");
//            base.put("tran_param", param);
           }


    /**获取post string信息
     * @return  合并参数后的加密报文字符串
     * */
    public String requestPost(){
        initCommon();
        return NetSecrty.endcodeRequest(base).toString();
    }

    /**获取 get 参数信息
     * @param url 访问的Url 地址
     *  @return  合并参数后的加密报文url
     * */
    public String requestGet(String url){
        initCommon();
        JSONObject json =  NetSecrty.endcodeRequest(base);
        String strUrl = "";
//        strUrl += "client_id=" + Config.CLIENT_ID;
        strUrl += "&content=" + json.optString("content").replaceAll("\\+", "_");
        strUrl += "&sign=" + json.optString("sign");

        String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
        return url + "?" + strUrl;
    }
}