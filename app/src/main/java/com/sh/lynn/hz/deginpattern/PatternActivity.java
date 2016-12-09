package com.sh.lynn.hz.deginpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatternActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("单例模式");
        ButterKnife.bind(this);

        String pattern = getIntent().getStringExtra("Pattern");

        tv_text.setText(Utils.getTextFromAssert(this,pattern));
    }

}
