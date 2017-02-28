package com.sh.lynn.hz.developbox.logframe;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/15.
 *
 *  网络传输接口
 */
public class NetLog implements ILog {

    @Override
    public void write(int level, String msg) {
        switch (level){
            case Log.ERROR:
                //NetUtils.sendLog(msg);

                break;
            case Log.WARN:
                break;
            case Log.INFO:
                break;
            default:
                break;
        }
    }

    @Override
    public void write(int level, File file) {
        switch (level){
            case Log.ERROR:
                if(file != null){
                    sendSever(file);
                }
                break;
            case Log.WARN:
                break;
            case Log.INFO:
                break;
            default:
                break;
        }
    }

    /**
     * 发送数据
     * */
    private void sendSever(final File file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fileInputStream.read(buffer)) >0){
                byteArrayOutputStream.write(buffer, 0, count);
            }
//            NetUtils.sendLog(new String(byteArrayOutputStream.toByteArray()), new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {//上传成功，删除文件
//                    file.delete();
//                }
//            }, new Response.ErrorListener(){
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                }
//            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
