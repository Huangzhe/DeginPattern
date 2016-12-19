package com.sh.lynn.hz.deginpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sh.lynn.hz.deginpattern.prototype.PrototypePattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatternActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.tv_result)
    TextView tv_result;
    String pattern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ButterKnife.bind(this);

         pattern = getIntent().getStringExtra("Pattern");
        String title = getIntent().getStringExtra("Title");
        toolbar.setTitle(title);
        tv_text.setText(Utils.getTextFromAssert(this,pattern));
    }
    @OnClick(R.id.btn_excu)
    public void excu(){
        switch (pattern){
            case "PrototypePattern.java":
                PrototypePattern pattern = new PrototypePattern();
                String text =  pattern.getCloneText();
                String text2 = pattern.getCloneTextDeep();
                tv_result.setText("浅拷贝："+text+"\n"+"深拷贝："+text2);
                tv_result.setHeight(2*80);
                break;
        }

    }

}
