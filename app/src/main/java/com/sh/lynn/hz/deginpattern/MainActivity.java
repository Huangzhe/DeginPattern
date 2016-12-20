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
        intent.putExtra("Title","单例模式");
        startActivity(intent);
    }

    /**
     * 工厂模式
     */
    @OnClick(R.id.button2)
    public void onClickFactoryPattern(){
        Intent intent = new Intent(this,PatternActivity.class);
        intent.putExtra("Pattern","FactoryPattern.java");
        intent.putExtra("Title","工厂模式");
        startActivity(intent);
    }

    /**
     * 模板方法模式
     */
    @OnClick(R.id.button4)
    public void onClickTemplateMethodPattern(){
        Intent intent = new Intent(this,PatternActivity.class);
        intent.putExtra("Pattern","TemplateMethodPattern.java");
        intent.putExtra("Title","模板方法模式");
        startActivity(intent);
    }

    /**
     * 建造者模式
     */
    @OnClick(R.id.button3)
    public void onClickBuildPattern(){
        Intent intent = new Intent(this,PatternActivity.class);
        intent.putExtra("Pattern","BuilderPattern.java");
        intent.putExtra("Title","建造者模式");
        startActivity(intent);
    }

    /**
     * 代理模式
     */
    @OnClick(R.id.button5)
    public void onClickProxyPattern(){
        Intent intent = new Intent(this,PatternActivity.class);
        intent.putExtra("Pattern","ProxyPattern.java");
        intent.putExtra("Title","代理模式");
        startActivity(intent);
    }

    /**
     * 原型模式
     */
    @OnClick(R.id.button6)
    public void onClickPrototypePattern(){
        Intent intent = new Intent(this,PatternActivity.class);
        intent.putExtra("Pattern","PrototypePattern.java");
        intent.putExtra("Title","原型模式");
        startActivity(intent);
    }

    /**
     * 原型模式
     */
    @OnClick(R.id.button7)
    public void onClickMediatorPattern(){
        Intent intent = new Intent(this,PatternActivity.class);
        intent.putExtra("Pattern","MediatorPattern.java");
        intent.putExtra("Title","中介者模式");
        startActivity(intent);
    }
}
