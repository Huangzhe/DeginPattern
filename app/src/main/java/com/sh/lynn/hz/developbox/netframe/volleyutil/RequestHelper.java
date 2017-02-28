package com.sh.lynn.hz.developbox.netframe.volleyutil;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sh.lynn.hz.developbox.App;
import com.sh.lynn.hz.developbox.event.BaseEvent;
import com.sh.lynn.hz.developbox.netframe.volleyutil.https.ExtHttpClientStack;
import com.sh.lynn.hz.developbox.netframe.volleyutil.https.SslHttpClient;
import com.sh.lynn.hz.developbox.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 * <p/>
 * http/https 网络请求
 * 支持post/get
 * File 上传
 */
public class RequestHelper {

    private RequestQueue queue;
    private Context context;
    private String cookie = "";
    public final static int HTTP_GET = 0x0001;
    public final static int HTTP_POST = 0x0002;

    private static RequestHelper instance;


    private RequestHelper() {
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }

    /**
     * @param context 设备上下文
     */
    public void init(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    /**
     * @param context 设备上下文
     */
    public void init(Context context, int keyStoreId) {
        this.context = context;
        InputStream keyStore = context.getResources().openRawResource(keyStoreId);
        queue = Volley.newRequestQueue(context, new ExtHttpClientStack(new SslHttpClient(keyStore, "123456", 80)));
    }

    public void cancel(String tag) {
        queue.cancelAll(tag);
    }


    /**
     * json格式，GET
     */
    public void getJson(String url, JSONObject param, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        requestJson(Request.Method.GET, url, param, base, tag, retryPolicy);
    }

    /**
     * json格式, POST
     */
    public void postJson(String url, JSONObject param, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        requestJson(Request.Method.POST, url, param, base, tag, retryPolicy);
    }

    //执行JSON格式的请求
    private void requestJson(int method, String url, JSONObject param, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                base.setMsg(jsonObject);
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
                EventBus.getDefault().post(base);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                base.setMsg(volleyError.getMessage()==null?"请求失败":volleyError.getMessage());
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
                EventBus.getDefault().post(base);
            }
        };
//        ArrayMap map = new ArrayMap();
//        map.put("showapi_appid", "30978");
//        map.put("showapi_sign", "7a1b389d30e547aea07908aaf8826d9b");
        JSRequest request = new JSRequest(method, url, param, listener, errorListener);
        //request.setHeaders(map);
        Log.e("JSRequest",param.toString());
        request.setTag(tag);
        request.setRetryPolicy(retryPolicy);
        request.setSendCookie(cookie);
        queue.add(request);
    }


    /**
     * str格式, GET
     */
    public void gettStr(String url, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        requestStr(Request.Method.GET, url, base, tag, retryPolicy);
    }


    /**
     * str格式, POST
     */
    public void postStr(String url, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        requestStr(Request.Method.POST, url, base, tag, retryPolicy);
    }

    private void requestStr(int method, String url, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                base.setMsg(s);
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
                EventBus.getDefault().post(base);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                base.setMsg(volleyError.getMessage()==null?"请求失败":volleyError.getMessage());
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
                EventBus.getDefault().post(base);
            }
        };

        StringRequest request = new StringRequest(method, url, listener, errorListener);
        request.setTag(tag);
        request.setRetryPolicy(retryPolicy);
        queue.add(request);
    }

    /**
     * 执行加密请求,根据情况修改
     */
    public void authRequest(String url, final JSONObject param, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                base.setMsg(s);
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
                EventBus.getDefault().post(base);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                base.setMsg(volleyError.getMessage()==null?"请求失败":volleyError.getMessage());
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
                EventBus.getDefault().post(base);
            }
        };

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String jsonStr = param.toString().replaceAll("\n", "");
                params.put("jsonStr", jsonStr);
                params.put("macStr", StringUtil.getMd5SaltStr(jsonStr));
                return params;
            }
        };
        request.setRetryPolicy(retryPolicy);
        request.setTag(tag);
        queue.add(request);
    }
    /**
     * 执行加密请求,根据情况修改
     */
    public void authRequest(int method, String url, final JSONObject param, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                base.setMsg(NetSecrty.decodeResponse(jsonObject));
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
                EventBus.getDefault().post(base);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("onErrorResponse",volleyError.getMessage()==null?"请求失败":volleyError.getMessage());
                String errorMsg = VolleyErrorHelper.getMessage(volleyError, App.getInstance());
                base.setMsg(errorMsg);
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
                EventBus.getDefault().post(base);
            }
        };

        method = (method == HTTP_POST) ? Request.Method.POST : Request.Method.GET;
        JSRequest request = new JSRequest(method, url, NetSecrty.endcodeRequest(param), listener, errorListener);
        Log.d("amtf", "链接环境---->" + url);
        request.setTag(tag);
        request.setRetryPolicy(retryPolicy);
//        request.setRetryPolicy(new DefaultRetryPolicy(80000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        request.setSendCookie(cookie);
        queue.add(request);
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }


    /**
     * 加密的用户处理
     */
    public void secrtyJson(int method, String url, final JSONObject param, final BaseEvent base, String tag) {
        final String urls="";
        Log.e("请求：","接口："+urls+"时间："+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("请求：","接口："+urls+"时间："+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                base.setMsg(NetSecrty.decodeResponse(jsonObject));
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
                EventBus.getDefault().post(base);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("onErrorResponse",volleyError.getMessage()==null?"请求失败":volleyError.getMessage());
                String errorMsg = VolleyErrorHelper.getMessage(volleyError, App.getInstance());
                base.setMsg(errorMsg);
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
                EventBus.getDefault().post(base);
            }
        };

        method = (method == HTTP_POST) ? Request.Method.POST : Request.Method.GET;
        JSRequest request = new JSRequest(method, url, NetSecrty.endcodeRequest(param), listener, errorListener);
        Log.d("amtf", "链接环境---->" + url);
        request.setTag(tag);
        request.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 1, 1.0f));
//        request.setRetryPolicy(new DefaultRetryPolicy(80000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        request.setSendCookie(cookie);
        queue.add(request);
    }

    /**
     * 超时时间设定，主动付款定制 默认85s
     */
    public void repayRequest(int method, String url, final JSONObject param, final BaseEvent base, String tag) {
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                base.setMsg(NetSecrty.decodeResponse(jsonObject));
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
                EventBus.getDefault().post(base);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                base.setMsg(volleyError.getMessage()==null?"请求失败":volleyError.getMessage());
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
                EventBus.getDefault().post(base);
            }
        };

        method = (method == HTTP_POST) ? Request.Method.POST : Request.Method.GET;
        JSRequest request = new JSRequest(method, url, NetSecrty.endcodeRequest(param), listener, errorListener);
        Log.d("amtf", "链接环境---->" + url);
        Log.d("amtf", "请求实体--->" + param.toString());
        request.setTag(tag);
//        request.setRetryPolicy(new DefaultRetryPolicy(Config.TIME_OUT * 1000, 1, 1.0f));
        request.setRetryPolicy(new DefaultRetryPolicy(85000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    public void Request(int method, String url, final JSONObject param, final BaseEvent base, String tag, RetryPolicy retryPolicy) {
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                base.setMsg(NetSecrty.decodeResponse(jsonObject));
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
                EventBus.getDefault().post(base);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                base.setMsg(volleyError.getMessage()==null?"请求失败":volleyError.getMessage());
                base.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
                EventBus.getDefault().post(base);
            }
        };

        method = (method == HTTP_POST) ? Request.Method.POST : Request.Method.GET;
        JSRequest request = new JSRequest(method, url, NetSecrty.endcodeRequest(param), listener, errorListener);
        Log.d("amtf", "链接环境---->" + url);
        request.setTag(tag);
        request.setRetryPolicy(retryPolicy );
//        request.setSendCookie(cookie);
        queue.add(request);
    }


    /**
     * 使用接口请求JSON
     * @param retryPolicy
     * @param listener 成功
     * @param errorListener 失败
     * @param tag
     * */
    public void sysRequest(int method, String url, final JSONObject param, String tag, RetryPolicy retryPolicy, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        method = (method == HTTP_POST) ? Request.Method.POST : Request.Method.GET;
        JSRequest request = new JSRequest(method, url, NetSecrty.endcodeRequest(param), listener, errorListener);
        request.setTag(tag);
        request.setRetryPolicy(retryPolicy);
        queue.add(request);
    }
}
