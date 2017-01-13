package com.sh.lynn.hz.developbox.uieffect;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.sh.lynn.hz.developbox.BaseActivity;
import com.sh.lynn.hz.developbox.R;
import com.sh.lynn.hz.developbox.uieffect.appbar.ColToolBarActivity;
import com.sh.lynn.hz.developbox.uieffect.listview.ListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UIEffectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uieffect);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        toolbar.setTitle("UI特效");
    }
    /**
     * 跳转UI特效
     */
    @OnClick(R.id.btn_appbar)
    public void onClickAppbar(){
        gotoAct(ColToolBarActivity.class);
    }

    /**
     * 跳转UI特效
     */
    @OnClick(R.id.btn_listView)
    public void onClickListView(){
        gotoAct(ListActivity.class);
    }
}
