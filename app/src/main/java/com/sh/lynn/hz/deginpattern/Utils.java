package com.sh.lynn.hz.deginpattern;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hyz84 on 16/12/9.
 */

public class Utils {

    public static String getTextFromAssert(Context context,String fileName){
        StringBuffer buffer = new StringBuffer();
        try {
            InputStream is =  context.getApplicationContext().getResources().getAssets().open(fileName);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String text =null;
            while ((text = reader.readLine())!=null)  {
                buffer.append(text+"\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

}
