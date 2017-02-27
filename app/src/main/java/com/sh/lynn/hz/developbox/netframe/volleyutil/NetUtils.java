package com.sh.lynn.hz.developbox.netframe.volleyutil;


import com.android.volley.DefaultRetryPolicy;
import com.sh.lynn.hz.developbox.event.YYPhotoEvent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/4.
 * <p/>
 * 网络请求处理
 */
public class NetUtils {

    private final static String NET_YYJOY = "http://route.showapi.com/341-2?showapi_appid=30978&showapi_sign=7a1b389d30e547aea07908aaf8826d9b&maxResult=1";// 易源笑图


    static class RequestBuilder {
        JSONObject param;
        JSONObject base;

        public RequestBuilder() {
            param = new JSONObject();
            base = new JSONObject();
        }

        public static RequestBuilder newInstance() {
            return new RequestBuilder();
        }

        /**
         * 设置内层参数
         */
        public RequestBuilder put(String key, Object value) {
            try {
                param.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        /**
         * 设置外层参数
         */
        public RequestBuilder putBase(String key, Object value) {
            try {
                base.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }

        /**
         * 设置基本信息
         */
        private void setBase(JSONObject json) throws JSONException {

//            json.put("showapi_appid", "30978");
//            json.put("showapi_sign", "7a1b389d30e547aea07908aaf8826d9b");
        }

        public JSONObject createJson() {
            //JSONObject json = base;
            try {

                setBase(base);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
            return base;
        }

        public JSONObject createNLJson() {
            JSONObject json = base;
            try {
                setBase(json);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
            return json;
        }


    }

    /**
     * 样例
     */
    public static void sample(String param1, String param2) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.put("p1", param1);
        param.put("p2", param2);
        RequestHelper.getInstance().postJson("http://url"/*地址*/, param.createNLJson(), null/*自定义事件*/, "tag"/*请求标记*/, new DefaultRetryPolicy());
    }

    public static void getPhoto(String num) {
        RequestBuilder param = RequestBuilder.newInstance();
        param.putBase("maxResult", num);
//        NET_YYJOY=  NET_YYJOY+"?showapi_appid=30978&showapi_sign=7a1b389d30e547aea07908aaf8826d9b";

        RequestHelper.getInstance().postJson(NET_YYJOY/*地址*/, param.createNLJson(), new YYPhotoEvent()/*自定义事件*/, "tag"/*请求标记*/, new DefaultRetryPolicy());
    }



}
