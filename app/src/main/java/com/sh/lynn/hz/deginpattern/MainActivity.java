package com.sh.lynn.hz.deginpattern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 单例模式
     */
    @OnClick(R.id.button)
    public void onClickSingletonPattern(){
    Intent intent = new Intent(this,PatternActivity.class);
    intent.putExtra("Pattern","SingletonBean.java");
    startActivity(intent);
    }
}
