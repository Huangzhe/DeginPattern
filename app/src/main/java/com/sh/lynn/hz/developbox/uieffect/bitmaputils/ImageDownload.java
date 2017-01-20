package com.sh.lynn.hz.developbox.uieffect.bitmaputils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/2.
 *
 * 圖片下載解析器
 */
public class ImageDownload extends  BaseImageDownloader{
    public ImageDownload(Context context, int connectTimeout, int readTimeout) {
        super(context, connectTimeout, readTimeout);
    }

    public ImageDownload(Context context) {
        super(context);
    }

    @Override
    protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
        InputStream stream =  super.getStreamFromNetwork(imageUri, extra);
        if (imageUri.indexOf('?') == -1){//普通url 无需校验
            return stream;
        }else if(imageUri.startsWith("http://img.yunrich.com")){
            return stream;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int count = 0;
        byte[] buffer = new byte[1024];
        while (true){
            count = stream.read(buffer);
            if (count <= 0){
                break;
            }
            outputStream.write(buffer,0,count);
        }
        stream.close();
        String content = "";
        //解析相关数据
        try {
            JSONObject respone = new JSONObject(outputStream.toString("UTF-8"));
            Log.d("respone",respone.toString());
            String return_code = respone.optString("return_code");
            if ("SUCCESS".equals(return_code)){
                if (!"SUCCESS".equals(respone.optString("return_code")) ){
                    Log.e("image-download",respone.optString("err_code")+":"+respone.optString("result_msg"));//错误消息
                }  else{
                    content = respone.optString("fileBytes");
                }
            }else{
                Log.e("image-download",respone.optString("return_code")+":"+respone.optString("return_msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException("Image request failed| format error---------");
        }
        if (TextUtils.isEmpty(content)){
            return null;
        }

        byte[] imgBuf = android.util.Base64.decode(content, Base64.DEFAULT);
        return new ByteArrayInputStream(imgBuf);
    }
}
