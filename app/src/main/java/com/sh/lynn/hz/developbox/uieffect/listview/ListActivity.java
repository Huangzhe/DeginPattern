package com.sh.lynn.hz.developbox.uieffect.listview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.sh.lynn.hz.developbox.R;
import com.sh.lynn.hz.developbox.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity implements XRefreshView.XRefreshViewListener{
    @BindView(R.id.list)
    ListView mListView;
    @BindView(R.id.swipe_refresh_layout)
    XRefreshView xRefreshView;

    LoadingDialog loadingDialog;
    List<String> mData = new ArrayList<>();
    private ArrayAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ButterKnife.bind(this);
//        toolbar.setTitle("ListView下拉刷新等");
        init();
    }

    private void init() {
        for(int x=1;x<8;x++){
            mData.add("A---"+x);
        }

      //  loadingDialog = new LoadingDialog(this, false);

        xRefreshView.setPullLoadEnable(true);

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setXRefreshViewListener(this);

        mAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mData);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {
        for(int x=0;x<5;x++){
            mData.add("B--"+x);
        }
        stopRefresh();
    }

    private void stopRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xRefreshView.stopLoadMore();
                xRefreshView.stopRefresh();
                mAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore(boolean isSilence) {
        for(int x=0;x<5;x++){
            mData.add("C--"+x);
        }
        stopRefresh();
    }

    @Override
    public void onRelease(float direction) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {

    }
}
