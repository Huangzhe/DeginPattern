package com.sh.lynn.hz.developbox.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/13.
 */
public class StringUtil {
    public static SimpleDateFormat yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    static SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");

    public static String nonce_str() {
        return Md5.getMd5(yyyyMMddHHmmssSSS.format(new Date()) + HHmmss.format(new Date()));
    }

    /**
     * 生成接口macStr值
     *
     * @param value
     * @return
     */
    public static String getMd5SaltStr(String value) {
        String result = getMd5(value );
        return result.substring(0, 16);
    }

    /**
     * Md5加密
     *
     * @param str
     * @return
     */
    public static String getMd5(String str) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();

    }

    public static boolean isNotNull(String value) {
        if (null == value || value.isEmpty())
            return false;
        else
            return true;
    }
}
