package com.sh.lynn.hz.developbox.netframe.volleyutil;

import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/8.
 */
public class JSRequest extends JsonObjectRequest {

    private Response.Listener<JSONObject> mListener;
    public String cookieFromResponse;
    private String mHeader;
    private Map<String, String> sendHeader=new HashMap<String, String>(1);
    public JSRequest(int method, String url, JSONObject jsonParam, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonParam, listener, errorListener);
        mListener = listener;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            mHeader = response.headers.toString();
            Log.w("LOG", "get headers in parseNetworkResponse " + response.headers.toString());
            //使用正则表达式从reponse的头中提取cookie内容的子串
            Pattern pattern= Pattern.compile("Set-Cookie.*?;");
            Matcher m=pattern.matcher(mHeader);
            if(m.find()){
                cookieFromResponse =m.group();
                Log.w("LOG","cookie from server "+ cookieFromResponse);
                //去掉cookie末尾的分号
                cookieFromResponse = cookieFromResponse.substring(11,cookieFromResponse.length()-1);
                Log.w("LOG","cookie substring "+ cookieFromResponse);
            }
            //将cookie字符串添加到jsonObject中，该jsonObject会被deliverResponse递交，调用请求时则能在onResponse中得到
            JSONObject jsonObject = new JSONObject(jsonString);
            if (cookieFromResponse != null){
                jsonObject.put("Cookie",cookieFromResponse);
            }
            Log.w("LOG","jsonObject "+ jsonObject.toString());
            return Response.success(jsonObject,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return sendHeader;
    }

    public void setSendCookie(String cookie){
        sendHeader.put("Cookie",cookie);
    }
    public void setHeaders(ArrayMap<String,String> map){
        sendHeader.putAll(map);
        //sendHeader.put("Cookie",cookie);
    }
}
