package com.sh.lynn.hz.deginpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sh.lynn.hz.deginpattern.Utils.tempBuffer;

public class PatternActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.tv_result)
    TextView tv_result;
    String pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
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
        tv_text.setText(Utils.getTextFromAssert(this, pattern));
    }

    @OnClick(R.id.btn_excu)
    public void excu() {
        tempBuffer.delete(0, tempBuffer.length());
        setText(pattern.substring(0, pattern.length() - 5));
    }

    private void setText(String className) {
        try {
            Class cls = Class.forName(getPackageName() + "." + className.toLowerCase() + "." + className);
            Object obj = cls.newInstance();
            // Method method = cls.getMethod("excuExample");
            Method[] methods = cls.getMethods();
            for (Method method : methods) {

                if (method.getDeclaringClass().getName().contains(className))
                    method.invoke(obj);
            }
            tv_result.setText(tempBuffer.toString());
            tv_result.setHeight(2 * 80);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
