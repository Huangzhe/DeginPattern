package com.sh.lynn.hz.developbox;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.sh.lynn.hz.developbox.deginpattern.PatternTypeActivity;
import com.sh.lynn.hz.developbox.netframe.volleyutil.VolleyDemoActivity;
import com.sh.lynn.hz.developbox.uieffect.UIEffectActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    /**
     * 跳转UI特效
     */
    @OnClick(R.id.btn_ui_effect)
    public void onClickUIEffect(){
        gotoAct(UIEffectActivity.class);
    }
    /**
     * 跳转UI特效
     */
    @OnClick(R.id.btn_net_frame)
    public void onClickNetFrame(){
        gotoAct(VolleyDemoActivity.class);
    }

    /**
     * 跳转模式
     */
    @OnClick(R.id.btn_designpattern)
    public void onClickPattern(){
        gotoAct(PatternTypeActivity.class);
    }

//    public void gotoAct(Class cls){
//        Intent intent = new Intent(this,cls);
//        startActivity(intent);
//    }

}
