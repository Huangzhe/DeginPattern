package com.sh.lynn.hz.developbox.netframe.okhttp;

import android.content.Context;
import android.util.Log;

import com.sh.lynn.hz.developbox.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/9.
 *
 * 网络请求工具类
 * 支持http/https
 * https时，把证书等导入到assets中
 */

public class OkUtils {
    static OkHttpClient client;

    static class OkImpl{
        static OkUtils instance = new OkUtils();
    }
    public static OkUtils instance(){
        return OkImpl.instance;
    }


    private OkUtils(){
    }

    /**初始化网络组件
     * @param outTime 超时时间
     * */
    public void init(int outTime){
        client = new OkHttpClient.Builder().connectTimeout(outTime, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(false)
                .build();
    }

    /**初始化网络组件,单向https
     * @param outTime 超时时间
     *    @param ctx 设备
     *        @param cerName 证书名称
     * */
    public void init(int outTime, Context ctx, String cerName){
        client = new OkHttpClient.Builder().connectTimeout(outTime, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(false)
                .sslSocketFactory(Https.getSSLSocketFactory(ctx,cerName))
                .build();
    }

    /**初始化网络组件, 双向https
     * @param outTime 超时时间
     *    @param ctx 设备
     *        @param cerName 证书名称
     *         @param bksName 客户端bks文件名
     *             @param password bks密码
     * */
    public void init(int outTime, Context ctx, String cerName, String bksName, String password){
        InputStream[] inputStream = new InputStream[1];
        InputStream bksStream = null;
        try {
            inputStream[0] = ctx.getResources().getAssets().open(cerName);
            bksStream = ctx.getResources().getAssets().open(bksName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Https.SSLParams sslParams = Https.getSslSocketFactory(inputStream, bksStream, password);
        client = new OkHttpClient.Builder().connectTimeout(outTime, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(false)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
    }



    public MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    public void post(String url, String json, BaseEvent evnet) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new NetCallback(evnet));
    }

    public void post(String url, String json, int timeout, BaseEvent evnet) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newBuilder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .build().newCall(request).enqueue(new NetCallback(evnet));
    }


    public void get(String url, BaseEvent evnet) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new NetCallback(evnet));
    }

    public void get(String url, int timeout, BaseEvent evnet)  {
        Request request = new Request.Builder()
                .url(url)
                .build();
         client.newBuilder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .build().newCall(request).enqueue(new NetCallback(evnet));
    }


    /**文件上传
     *  仅仅支持http文件上传，响应结果均为加密, 响应结果直接返回字符串/或者json格式字符串
     * */
    public void upload(String url, List<File> files, BaseEvent evnet){
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (File file:files){
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            builder.addFormDataPart("image",file.getName(),fileBody);
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new NetCallback(evnet, false));
    }

    class NetCallback implements Callback{
        BaseEvent evnet;
        boolean isEncode = true;

        public NetCallback(BaseEvent evnet){
            this.evnet = evnet;
        }
        public NetCallback(BaseEvent evnet, boolean isencode){
            this.evnet = evnet; isEncode=isencode;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            evnet.setStatus(BaseEvent.EVENT_STATUS.EVENT_ERROR);
            evnet.setMsg(e.getMessage());
            EventBus.getDefault().post(evnet);
            Log.d("OkUtils", e.getMessage()==null?"空":e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            evnet.setStatus(BaseEvent.EVENT_STATUS.EVENT_OK);
            try {
                if (isEncode){
                    evnet.setMsg(NetSecrty.decodeResponse(new JSONObject(response.body().string())));
                }else{
                    evnet.setMsg(response.body().string());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            EventBus.getDefault().post(evnet);
        }
    };
}