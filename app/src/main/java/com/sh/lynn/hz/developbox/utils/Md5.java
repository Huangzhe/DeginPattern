package com.sh.lynn.hz.developbox.utils;

import java.security.MessageDigest;

/**
 * Created by zt on 2015/9/11.
 */
public class Md5 {

    public static String getMd5(String str){
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            byte[] b = md.digest();

            int i;


            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//	    System.out.println("result: " + buf.toString()); //32位的加密
//		System.out.println("result: " + buf.toString().substring(8,24)); //16位的加密
//      System.out.println("result: " + md.digest());
        //toUpperCase()转大写
        return buf.toString();
    }

}
