package com.sh.lynn.hz.developbox;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sh.lynn.hz.developbox.utils.StatusBarUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.StatusBarLightMode(this);
        }
    }

    protected void gotoAct(Class cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);

    }

}
